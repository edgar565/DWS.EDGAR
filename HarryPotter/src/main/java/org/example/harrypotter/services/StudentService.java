package org.example.harrypotter.services;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
    Student getStudentByName(String name);
    List<Student> filterStudents(String name, String patronus);
    void createStudent(House house, Student student);
    void updateStudent(String name, Student student);
}
