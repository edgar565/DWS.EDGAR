package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.StudentRepository;
import org.example.harrypotter.services.StudentService;
import org.example.harrypotter.services.StudentServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentsController {
    private StudentService studentService = new StudentServiceImplementation(new StudentRepository());

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/student?name={name}")
    public String getStudent(Model model, @PathVariable String name) {
        Student student = studentService.getStudentByName(name);
        model.addAttribute("student", student);
        return "student";
    }
}