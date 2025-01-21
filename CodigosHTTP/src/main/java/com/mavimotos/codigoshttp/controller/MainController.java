package com.mavimotos.codigoshttp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/prueba")
    public String prueba() {
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }
}