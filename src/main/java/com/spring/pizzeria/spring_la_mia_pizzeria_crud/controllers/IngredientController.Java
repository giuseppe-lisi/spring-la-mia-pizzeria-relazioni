package com.spring.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @GetMapping("/")
    public String index() {
        return "ingredients/index";
    }
    

}
