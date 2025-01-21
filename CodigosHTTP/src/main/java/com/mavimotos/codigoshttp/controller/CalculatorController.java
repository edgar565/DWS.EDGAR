package com.mavimotos.codigoshttp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CalculatorController {

    @GetMapping("/suma/{num1}/{num2}")
    public String suma(@PathVariable String num1, @PathVariable String num2, Model model) {
        try {
            int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
            model.addAttribute("operacion", "Suma");
            model.addAttribute("resultado", resultado);
            return "resultado";
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los parámetros no son números");
        }
    }

    @GetMapping("/division/{num1}/{num2}")
    public String division(@PathVariable String num1, @PathVariable String num2, Model model) {
        try {
            int divisor = Integer.parseInt(num2);
            if (divisor == 0) {
                throw new ArithmeticException("División por 0 no permitida");
            }
            double resultado = Integer.parseInt(num1) / (double) divisor;
            model.addAttribute("operacion", "División");
            model.addAttribute("resultado", resultado);
            return "resultado";
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los parámetros no son números");
        }
    }

    @GetMapping("/factorial/{num}")
    public String factorial(@PathVariable int num, Model model) {
        if (num < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El factorial no está definido para números negativos");
        }
        if (num > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número demasiado grande para calcular el factorial");
        }
        long resultado = 1;
        for (int i = 1; i <= num; i++) {
            resultado *= i;
        }
        model.addAttribute("operacion", "Factorial");
        model.addAttribute("resultado", resultado);
        return "resultado";
    }

    @GetMapping("/raiz/{num}")
    public String raiz(@PathVariable String num) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Acceso no autorizado para la operación raíz");
    }
}