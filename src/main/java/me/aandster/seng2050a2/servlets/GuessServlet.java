package me.aandster.seng2050a2.servlets;

import me.aandster.seng2050a2.model.GtwGame;
import me.aandster.seng2050a2.persistence.GamePersistenceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuessServlet extends HttpServlet {

    private static final String GAME_PERSISTENCE_FILEPATH = "/WEB-INF/data/game_saves.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Get the guessed letter and ensure a value was provided. */
        final String guessSubmitted = req.getParameter("guess");
        boolean invalidGuess = false;
        if (guessSubmitted == null || guessSubmitted.isEmpty() || GtwGame.isInvalidGuess(guessSubmitted)) {
            doGet(req, resp);
            return;
        }

        /* Check username is in session */
        final String playerUsername = req.getSession().getAttribute("username").toString();

        /* Get the game from the game persistence service */
        final GamePersistenceService gamePersistenceService = new GamePersistenceService(getServletContext().getRealPath(GAME_PERSISTENCE_FILEPATH));
        final GtwGame gameModel = gamePersistenceService.get(playerUsername);
        if (gameModel == null) throw new IOException("Game could not be retrieved from persistence.");


        /* Submit guess and persist the game */
        String errorMessage = null;
        try {
            int indexOfGuessSubmitted = gameModel.hasLetterBeenGuessedAlready(guessSubmitted.toCharArray()[0]);
            boolean isDuplicatedGuess = indexOfGuessSubmitted > -1;
            if (isDuplicatedGuess) {
                errorMessage = "The letter '" + guessSubmitted + "' was submitted already in round " + indexOfGuessSubmitted;
            } else {
                gameModel.makeGuess(guessSubmitted.toCharArray()[0]);
                gamePersistenceService.replace(playerUsername, gameModel);
                gamePersistenceService.writeToFile();
            }
        } catch (IllegalArgumentException ignored) {
        }

        /* Redirect to play servlet */
        if (errorMessage != null) req.setAttribute("errorMessage", errorMessage);
        req.getRequestDispatcher("play").forward(req, resp);
    }
}
