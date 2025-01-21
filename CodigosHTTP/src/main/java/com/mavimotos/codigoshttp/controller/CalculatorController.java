package com.mavimotos.codigoshttp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CalculatorController {

    @GetMapping("/suma/{num1}/{num2}")
    public String suma(@PathVariable String num1, @PathVariable String num2) {
        try {
            int result = Integer.parseInt(num1) + Integer.parseInt(num2);
            return "Resultado: " + result;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros no válidos");
        }
    }

    @GetMapping("/division/{num1}/{num2}")
    public String division(@PathVariable String num1, @PathVariable String num2) {
        try {
            int divisor = Integer.parseInt(num2);
            if (divisor == 0) {
                throw new ArithmeticException();
            }
            int result = Integer.parseInt(num1) / divisor;
            return "Resultado: " + result;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros no válidos");
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "División por 0 no permitida");
        }
    }

    @GetMapping("/factorial/{num}")
    public String factorial(@PathVariable String num) {
        try {
            int number = Integer.parseInt(num);
            if (number < 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Factorial no definido para negativos");
            }
            if (number > 100) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número demasiado grande");
            }
            long result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            return "Resultado: " + result;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetro no válido");
        }
    }

    @GetMapping("/raiz/{num}")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String raiz(@PathVariable String num) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autorizado para usar esta ruta");
    }
}