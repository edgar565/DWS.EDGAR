package org.edgar.webtareas.controller;

import org.edgar.webtareas.entities.Trabajador;
import org.edgar.webtareas.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping("{trabajador_id}")
    public Trabajador getTrabajador(@PathVariable("trabajador_id") int trabajador_id) {
        return trabajadorService.getTrabajador(trabajador_id);
    }


}