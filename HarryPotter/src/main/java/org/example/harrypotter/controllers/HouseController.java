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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HouseController {

    private HouseService houseService = new HouseServiceImplementation(new HouseRepository());
    private StudentService StudentService = new StudentServiceImplementation(new StudentRepository());

    @GetMapping("/houses")
    public String getHouses(Model model) {
        List<House> houses = houseService.getHouses();
        model.addAttribute("houses", houses);
        return "houses";
    }

    @GetMapping("/house/{name}")
    public String getHouse(Model model, @PathVariable String name) {
        House house = houseService.getHouseByName(name);
        model.addAttribute("house", house);
        List<Student> students = StudentService.getStudents();
        model.addAttribute("students", students);
        return "house";
    }

}
