package ru.innopolis.stc12.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.repository.dao.UserDaoImpl;
import ru.innopolis.stc12.servlets.service.UserService;
import ru.innopolis.stc12.servlets.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(DashboardController.class);
    static final String LOGIN = "login";
    final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public void init() {
        // Do nothing
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if ("logout".equals(req.getParameter("action"))) {
                req.getSession().invalidate();
            }

            if (req.getSession().getAttribute(LOGIN) != null) {
                resp.sendRedirect("/inner/dashboard");
            } else {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter(LOGIN);
            String password = req.getParameter("password");
            if (userService.checkAuth(username, password)) {
                int role = userService.getRole(username);
                req.getSession().setAttribute(LOGIN, username);
                req.getSession().setAttribute("role", role);
                resp.sendRedirect("/inner/dashboard");
            } else {

                resp.sendRedirect("/login?action=wrongUser");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
