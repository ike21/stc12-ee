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

public class RegistrationServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if (username != null && password != null) {
                userService.addUser(username, password);
                resp.sendRedirect("/login");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
