package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.StudentRepository;
import org.example.harrypotter.services.StudentService;
import org.example.harrypotter.services.StudentServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentsController {
    private StudentService studentService = new StudentServiceImplementation(new StudentRepository());

    @GetMapping("/students")
    public String getStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String patronus,
            Model model) {
        // Llama al servicio con los filtros
        List<Student> filteredStudents = studentService.filterStudents(name, patronus);
        model.addAttribute("students", filteredStudents);
        return "students";
    }

    @GetMapping("/student")
    public String getStudent(Model model, @RequestParam String name) {
        Student student = studentService.getStudentByName(name);
        model.addAttribute("student", student);
        return "student";
    }
}