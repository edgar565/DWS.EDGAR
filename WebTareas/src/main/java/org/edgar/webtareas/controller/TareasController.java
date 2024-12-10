package org.edgar.webtareas.controller;

import org.edgar.webtareas.entities.Tarea;
import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.service.TareasService;
import org.edgar.webtareas.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TareasController {

     private final TareasService tareasService;
    private final TrabajadorService trabajadorService;

    public TareasController(TareasService tareaservice, TrabajadorService trabajadorService) {
         this.tareasService = tareaservice;
         this.trabajadorService = trabajadorService;
    }

    @GetMapping("/tarea/create")
    public String createTarea(Model model) {
        model.addAttribute("trabajadores", trabajadorService.findAll());
        model.addAttribute("tarea", new Tarea());
        return "create_tarea";
    }

    @PostMapping("/tarea/create")
    public String createTarea(Tarea tarea) {
        tareasService.save(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/tareas")
    public String getTareas(Model model) {
        List<Tarea> tareas = tareasService.findAll();
        model.addAttribute("tareas", tareas);
        return "tareas";
    }

    @GetMapping("/tarea/edit/{tarea_id}")
    public String getEditTarea(@PathVariable("tarea_id") Long tarea_id, Model model) {
        model.addAttribute("tarea", tareasService.findById(tarea_id));
        return "edit_tarea";
    }

    @PostMapping("/tarea/edit/{tarea_id}")
    public String editTarea(@PathVariable("tarea_id") Long tarea_id, Tarea tarea) {
        tareasService.editTarea(tarea_id, tarea);
        return "redirect:/tarea/" + tarea_id;
    }

    @GetMapping("/tarea/{tarea_id}")
    public String getTarea(@PathVariable("tarea_id") Long tarea_id, Model model) {
        model.addAttribute("tarea", tareasService.findById(tarea_id));
        return "tarea";
    }




}