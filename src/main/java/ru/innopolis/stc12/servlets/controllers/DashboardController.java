package ru.innopolis.stc12.servlets.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(DashboardController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        }
    }


}
