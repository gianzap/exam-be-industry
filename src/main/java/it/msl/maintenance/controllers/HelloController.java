package it.msl.maintenance.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// @RestController = @Controller + @ResponseBody: i valori restituiti diventano il body JSON della risposta
@RestController
// prefisso comune a tutti gli endpoint di questa classe
@RequestMapping("/hello")
public class HelloController {

    // risponde a GET /hello
    @GetMapping
    public Map<String, String> hello() {
        // Map.of viene serializzata in JSON: {"message":"Hello MSL!"}
        return Map.of("message", "Hello MSL!");
    }
}
