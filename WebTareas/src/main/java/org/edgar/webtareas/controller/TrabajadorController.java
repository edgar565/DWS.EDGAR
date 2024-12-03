package org.edgar.webtareas.controller;

import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping("/trabajador/create")
    public String createTrabajador(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        return "create_trabajador";
    }

    @GetMapping("/trabajadores")
    public String getTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorService.findAll();
        model.addAttribute("trabajadores", trabajadores);
        return "trabajadores";
    }

    @GetMapping("/trabajador/edit/{trabajador_id}")
    public String getEditTrabajador(@PathVariable("trabajador_id") Long trabajador_id, Model model) {
        model.addAttribute("trabajador", trabajadorService.findById(trabajador_id));
        return "edit_trabajador";
    }

    @PostMapping("/trabajador/edit/{trabajador_id}")
    public String editTrabajador(@PathVariable("trabajador_id") Long trabajador_id, String nombre, int edad) {
        trabajadorService.editTrabajador(trabajador_id, nombre, edad);
        return "redirect:/trabajador/" + trabajador_id;
    }
}