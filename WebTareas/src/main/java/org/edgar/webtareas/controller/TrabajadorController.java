package org.edgar.webtareas.controller;

import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.service.EquipoService;
import org.edgar.webtareas.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class TrabajadorController {

    private final TrabajadorService trabajadorService;
    private final EquipoService equipoService;

    public TrabajadorController(TrabajadorService trabajadorService, EquipoService equipoService) {
        this.trabajadorService = trabajadorService;
        this.equipoService = equipoService;
    }

    @GetMapping("/trabajador/create")
    public String createTrabajador(Model model) {
        model.addAttribute("equipos", equipoService.findAll());
        model.addAttribute("trabajador", new Trabajador());
        return "create_trabajador";
    }

    @PostMapping("/trabajador/create")
    public String createTrabajador(Trabajador trabajador) {
        trabajadorService.save(trabajador);
        return "redirect:/trabajadores";
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
    public String editTrabajador(@PathVariable("trabajador_id") Long trabajador_id, Trabajador trabajador) {
        trabajadorService.editTrabajador(trabajador_id, trabajador);
        return "redirect:/trabajador/" + trabajador_id;
    }

    @GetMapping("/trabajador/{trabajador_id}")
    public String getTrabajador(@PathVariable("trabajador_id") Long trabajador_id, Model model) {
        model.addAttribute("trabajador", trabajadorService.findById(trabajador_id));
        return "trabajador";
    }
}