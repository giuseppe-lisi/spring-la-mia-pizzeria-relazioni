package com.spring.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Ingredient;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Offer;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Pizza;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.IngredientsRepository;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.OfferRepository;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.PizzaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzasRepo;

    @Autowired
    private IngredientsRepository ingredientRepo;

    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String name) {
        if (name != null) {
            List<Pizza> result = pizzasRepo.findByNameContaining(name);
            model.addAttribute("pizzas", result);
        } else {
            List<Pizza> result = pizzasRepo.findAll();
            model.addAttribute("pizzas", result);
        }

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Pizza pizza = pizzasRepo.findById(id).get();

        model.addAttribute(pizza);

        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepo.findAll());

        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute Pizza pizza,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());
            return "pizzas/create";
        }

        pizzasRepo.save(pizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Pizza pizza = pizzasRepo.findById(id).get();

        model.addAttribute("ingredients", ingredientRepo.findAll());
        model.addAttribute("pizza", pizza);

        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());
            return "/pizzas/edit";
        }

        pizzasRepo.save(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        pizzasRepo.deleteById(id);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/add-offer")
    public String addOffer(@PathVariable int id, Model model) {
        Offer offer = new Offer();
        offer.setPizza(pizzasRepo.findById(id).get());

        model.addAttribute("offer", offer);

        return "offers/create";
    }
}
