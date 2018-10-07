package ru.innopolis.stc12.servlets.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.servlets.pojo.City;
import ru.innopolis.stc12.servlets.pojo.Student;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc12.servlets.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.servlets.repository.dao.mappers.SQL;
import ru.innopolis.stc12.servlets.repository.dao.mappers.StudentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    static final Logger LOGGER = Logger.getLogger(StudentDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addStudent(Student student) {
        Connection connection = connectionManager.getConnection();
        CityDaoImpl cityDao = new CityDaoImpl();
        if (student != null && student.getCity() != null) {
            if (cityDao.getCityByName(student.getCity().getName()) == null) {
                cityDao.addCity(student.getCity());
            }
            student.setCity(cityDao.getCityByName(student.getCity().getName()));

            try (PreparedStatement statement = connection.prepareStatement(
                    SQL.INSERT_STUDENT)
            ) {
                StudentMapper.getStateFromStudent(statement, student);
                statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
        CityDaoImpl cityDao = new CityDaoImpl();
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.SELECT_STUDENT_BY_ID)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = StudentMapper.getStudentFromSet(resultSet, cityDao.getCityById(resultSet.getInt("city")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        CityDaoImpl cityDao = new CityDaoImpl();
        if (student != null && student.getCity() != null) {
            if (cityDao.getCityById(student.getCity().getId()) == null) {
                cityDao.addCity(student.getCity());
            }

            if (student.getId() != 0) {
                Connection connection = connectionManager.getConnection();
                try (PreparedStatement statement = connection.prepareStatement(
                        SQL.UPDATE_STUDENT_BY_ID)
                ) {
                    StudentMapper.getStateFromStudent(statement, student);
                    statement.setInt(6, student.getId());
                    statement.execute();
                } catch (SQLException e) {
                    LOGGER.error(e);
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStudentById(int id) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.DELETE_STUDENT_BY_ID)
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentByName(Student student) {
        if (student.getName() != null) {
            Connection connection = connectionManager.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL.DELETE_STUDENT_BY_NAME)) {
                statement.setString(1, student.getName());
                statement.execute();
            } catch (SQLException e) {
                LOGGER.error(e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        CityDaoImpl cityDao = new CityDaoImpl();
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                SQL.SELECT_ALL_STUDENTS)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = cityDao.getCityById(resultSet.getInt("city"));
                    Student student = StudentMapper.getStudentFromSet(resultSet, city);
                    result.add(student);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return result;
        }
        return result;
    }
}
