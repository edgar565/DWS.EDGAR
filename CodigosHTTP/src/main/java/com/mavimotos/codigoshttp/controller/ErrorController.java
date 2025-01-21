package com.mavimotos.codigoshttp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @GetMapping("/error401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorized() {
        return "error/401";
    }

    @GetMapping("/error403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden() {
        return "error/403";
    }

    @GetMapping("/error500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverError() {
        return "error/500";
    }
}