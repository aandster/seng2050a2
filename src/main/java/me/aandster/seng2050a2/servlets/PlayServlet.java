package me.aandster.seng2050a2.servlets;

import me.aandster.seng2050a2.model.GameBean;
import me.aandster.seng2050a2.model.GtwGame;
import me.aandster.seng2050a2.persistence.GamePersistenceService;
import me.aandster.seng2050a2.persistence.SecretWordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayServlet extends HttpServlet {

    private static final String SECRET_WORD_FILE_PATH = "/WEB-INF/data/secret_words.txt";
    private static final String GAME_PERSISTENCE_FILEPATH = "/WEB-INF/data/game_saves.txt";


    @Override
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Check username is in session, if not, redirect to start page */
        final String username = req.getSession().getAttribute("username").toString();
        if (username == null || username.isEmpty()) {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/start");
            return;
        }

        /* Check if user selected to start new game */
        final boolean overwritePreviousSavedGame = Boolean.parseBoolean(req.getParameter("create_new_game"));

        /* Check if user has game saved already */
        final GamePersistenceService persistenceService = new GamePersistenceService(
                getServletContext().getRealPath(GAME_PERSISTENCE_FILEPATH));

        GtwGame gameModel = persistenceService.get(username);

        /* Create a new game if required: if no persisted game found, or if manually requested */
        if (overwritePreviousSavedGame || gameModel == null) {

            /* Need to provide the new game with a secret word */
            SecretWordService secretWordService = new SecretWordService(getServletContext().getRealPath(SECRET_WORD_FILE_PATH));

            /* Create new game */
            gameModel = new GtwGame(username, secretWordService.getSecretWord());
            persistenceService.remove(username);
            persistenceService.put(username, gameModel);
            persistenceService.writeToFile();
        }

        /* Check if the gme is solved */
        boolean isGameSolved = gameModel.isSolved();

        /* Populate a bean */
        GameBean gameBean = gameModel.getBean();

        /* Add bean to request */
        req.setAttribute("game", gameBean);

        if (gameModel.isSolved()) {
            req.getRequestDispatcher("/WEB-INF/jsp/finished.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/play.jsp").forward(req, resp);
        }
    }

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
