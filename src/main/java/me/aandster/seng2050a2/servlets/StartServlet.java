package me.aandster.seng2050a2.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Forward to the JSP Servlet */
        req.getRequestDispatcher("/WEB-INF/jsp/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* Check if username provided; if so, add into session; if not, forward back to beginning. */
        final String username = req.getParameter("username");
        if (username == null || username.isEmpty()) {
            doGet(req, resp);
        } else {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("play");
        }
    }
}
