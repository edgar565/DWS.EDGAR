package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.HouseRepository;
import org.example.harrypotter.repositories.StudentRepository;
import org.example.harrypotter.services.HouseService;
import org.example.harrypotter.services.HouseServiceImplementation;
import org.example.harrypotter.services.StudentService;
import org.example.harrypotter.services.StudentServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class IndexController {
    private HouseService houseService = new HouseServiceImplementation(new HouseRepository());
    private StudentService studentService = new StudentServiceImplementation(new StudentRepository());

    @GetMapping("/")
    public String getIndex(Model model) {
        List<House> houses = houseService.getHouses();
        List<Student> students = studentService.getStudents();
        Random random = new Random();
        House house = houses.get(random.nextInt(houses.size()));
        Student student = students.get(random.nextInt(students.size()));
        model.addAttribute("house", house);
        model.addAttribute("student", student);
        return "index";
    }
}
