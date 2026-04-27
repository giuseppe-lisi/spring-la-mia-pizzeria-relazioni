package com.spring.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Ingredient;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Pizza;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.IngredientsRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final PizzaRepository pizzaRepository;
    @Autowired
    private IngredientsRepository ingredientRepository;

    IngredientController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        model.addAttribute("ingredients", ingredients);

        return "ingredients/index";
    }

    @GetMapping("/add")
    public String create(Model model) {
        Ingredient ingredient = new Ingredient();

        model.addAttribute("ingredient", ingredient);

        return "ingredients/create";
    }

    @PostMapping("/add")
    public String store(@Valid @ModelAttribute Ingredient ingredient, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "ingredients/create";
        }

        ingredientRepository.save(ingredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        Ingredient ingredient = ingredientRepository.findById(id).get();

        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }

        ingredientRepository.deleteById(id);
        
        return "redirect:/ingredients";
    }
    

}
