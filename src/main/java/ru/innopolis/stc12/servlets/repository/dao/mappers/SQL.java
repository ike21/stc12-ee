package ru.innopolis.stc12.servlets.repository.dao.mappers;

public class SQL {
    public static final String INSERT_STUDENT = "INSERT INTO students VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE id = ?";
    public static final String UPDATE_STUDENT_BY_ID = "UPDATE students SET name=?, family_name=?, age=?, contact=?, city=? WHERE id=?";
    public static final String DELETE_STUDENT_BY_ID = "DELETE FROM students WHERE id=?";
    public static final String DELETE_STUDENT_BY_NAME = "DELETE FROM students WHERE name = ?";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
    public static final String INSERT_CITIES = "INSERT INTO cities VALUES(DEFAULT, ?, ?)";
    public static final String SELECT_CITY_BY_ID = "SELECT * FROM cities WHERE id = ?";
    public static final String SELECT_CITY_BY_NAME = "SELECT * FROM cities WHERE name = ?";
    public static final String UPDATE_CITY_BY_ID = "UPDATE cities SET name=?, citizens=? WHERE id=?";
    public static final String DELETE_CITY_BY_ID = "DELETE FROM cities WHERE id=?";
    public static final String DELETE_CITY_BY_NAME = "DELETE FROM cities WHERE name = ?";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM \"users\" WHERE \"users\".username = ?";
    public static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?)";

    private SQL() {
    }
}
