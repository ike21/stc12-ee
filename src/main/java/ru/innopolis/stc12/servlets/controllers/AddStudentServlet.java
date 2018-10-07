package ru.innopolis.stc12.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.pojo.Student;
import ru.innopolis.stc12.servlets.service.StudentService;
import ru.innopolis.stc12.servlets.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(AddStudentServlet.class);
    final StudentService studentService = new StudentServiceImpl();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/pages/addStudent.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            String familyName = req.getParameter("family_name");
            String age = req.getParameter("age");
            String contact = req.getParameter("contact");
            String city = req.getParameter("city");
            if (name != null && familyName != null && age != null && contact != null && city != null) {
                studentService.addStudent(new Student(0, name, familyName, Integer.parseInt(age), contact, new City(city)));
                resp.sendRedirect("/students");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
