package com.mavimotos.codigoshttp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ErrorController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/prueba")
    public String prueba() {
        throw new ResponseStatusException(NOT_FOUND, "Unable to fincd resource");
        //return "prueba";
    }
    @GetMapping("/error401")
    public String error401() {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autorizado para acceder a este recurso");
    }

    @GetMapping("/error403")
    public String error403() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso prohibido a este recurso");
    }

    @GetMapping("/error500")
    public String error500() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
    }
}