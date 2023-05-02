package com.example.lab5_20191822.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("inicio")
    public String index(){
        return "Recursos Humanos - TravelTrip";
    }
}
