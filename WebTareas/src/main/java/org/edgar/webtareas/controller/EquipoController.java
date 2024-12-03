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
import java.util.Set;

@Controller
public class EquipoController {

    private EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }
    @GetMapping("/equipo/{equipo_id}")
    public String getEquipo(@PathVariable("equipo_id") Long equipo_id, Model model) {
        /*Equipo equipo = equipoService.findById(equipo_id);
        Set<Trabajador> trabajadores = equipoService.findById(equipo_id).getTrabajadores();
        model.addAttribute("trabajadores", trabajadores);*/
        model.addAttribute("equipo", equipoService.findById(equipo_id));
        return "equipo";
    }

    @GetMapping("/equipos")
    public String getEquipos(Model model) {
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("equipos", equipos);
        return "equipos";
    }

    @GetMapping("/equipo/create")
    public String createEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "create_equipo";
    }

    @PostMapping("/equipo/create")
    public String createEquipo(Equipo equipo) {
        equipoService.save(equipo);
        return "redirect:/equipos";
    }
    @GetMapping("/equipo/edit/{equipo_id}")
    public String getEditEquipo(@PathVariable("equipo_id") Long equipo_id, Model model) {
        List<Trabajador> trabajadores = equipoService.findAllTrabajadores();
        model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("equipo", equipoService.findById(equipo_id));
        return "edit_equipo";
    }
    @PostMapping("/equipo/edit/{equipo_id}")
    public String editEquipo(@PathVariable("equipo_id") Long equipo_id, Equipo equipo) {
        equipoService.editEquipo(equipo_id, equipo);
        return "redirect:/equipos";
    }
}