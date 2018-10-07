package ru.innopolis.stc12.servlets.repository.dao;


import ru.innopolis.stc12.servlets.pojo.Student;

import java.util.List;

public interface StudentDao {
    boolean addStudent(Student student);

    Student getStudentById(int id);

    boolean updateStudent(Student student);

    boolean deleteStudentById(int id);

    boolean deleteStudentByName(Student student);

    List<Student> getAllStudents();
}
