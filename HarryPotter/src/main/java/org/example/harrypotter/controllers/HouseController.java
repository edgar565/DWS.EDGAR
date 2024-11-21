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
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/house/create")
    public String createHouse(Model model) {
        model.addAttribute("house", new House());
        return "create_house";
    }
    @PostMapping("/house/create")
    public String createHouse(House house) {
        houseService.createHouse(house);
        return "redirect:/houses";
    }
    @GetMapping("/house/update/{name}")
    public String updateHouse(Model model, @PathVariable String name) {
        model.addAttribute("house", houseService.getHouseByName(name));
        return "update_house";
    }
    @PostMapping("/house/update/{name}")
    public String updateHouse(@PathVariable String name, House house) {
        houseService.updateHouse(name, house);
        return "redirect:/houses";
    }
    @GetMapping("/house/delete/{name}")
    public String deleteHouse(@PathVariable String name) {
        houseService.deleteHouse(name);
        return "redirect:/houses";
    }
    @GetMapping("/house/createstudent/{name}")
    public String createStudent(Model model, @PathVariable String name) {
        model.addAttribute("house", houseService.getHouseByName(name));
        model.addAttribute("student", new Student());
        return "create_student";
    }
    @PostMapping("/house/createstudent/{name}")
    public String createStudent(@PathVariable String name, Student student) {
        House house = houseService.getHouseByName(name);
        StudentService.createStudent(house, student);
        return "redirect:/houses";
    }
    @GetMapping("/student/update/{name}")
    public String updateStudent(Model model, @PathVariable String name) {
        model.addAttribute("houses", houseService.getHouses());
        model.addAttribute("student", StudentService.getStudentByName(name));
        return "update_student";
    }

    @PostMapping("/student/update/{name}")
    public String updateStudent(@PathVariable String name, Student student) {
        StudentService.updateStudent(name, student);
        return "redirect:/house/{name}";
    }
}