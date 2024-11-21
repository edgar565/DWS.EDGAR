package org.example.harrypotter.services;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.StudentRepository;

import java.util.List;

public class StudentServiceImplementation implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
    @Override
    public Student getStudentByName(String name) {
        return studentRepository.getStudent(name);
    }

    @Override
    public List<Student> filterStudents(String name, String patronus) {
        List<Student> students = getStudents();
        return students.stream()
                .filter(s -> (name == null || s.getName().toLowerCase().contains(name.toLowerCase())) &&
                        (patronus == null || s.getPatronus().toLowerCase().contains(patronus.toLowerCase())))
                .toList();
    }
    @Override
    public void createStudent(House house, Student student) {
        studentRepository.createStudent(house, student);
    }
    @Override
    public void updateStudent(String name, Student student) {
        studentRepository.updateStudent(name, student);
    }


}
