package org.edgar.webtareas.controller;

import org.edgar.webtareas.entities.Equipo;
import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipo/create")
    public String createEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "create_equipo";
    }
    @GetMapping("/equipos")
    public String getEquipos(Model model) {
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("trabajadores", equipos);
        return "equipos";
    }

    @GetMapping("/equipo/{equipo_id}")
    public String getEquipo(@PathVariable("equipo_id") Long equipo_id, Model model) {
            Equipo equipo = equipoService.findById(equipo_id);
        model.addAttribute("trabajador", equipo);
        return "equipo";
    }

    @GetMapping("/equipo/edit/{equipo_id}")
    public String getEditEquipo(@PathVariable("equipo_id") Long equipo_id, Model model) {
        model.addAttribute("equipo", equipoService.findById(equipo_id));
        return "edit_equipo";
    }

    @PostMapping("/equipo/edit/{equipo_id}")
    public String editEquipo(@PathVariable("equipo_id") Long equipo_id, String nombre) {
        equipoService.editEquipo(equipo_id, nombre);
        return "redirect:/equipo/" + equipo_id;
    }
}
