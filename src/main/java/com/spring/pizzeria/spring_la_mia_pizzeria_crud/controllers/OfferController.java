package com.spring.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Offer;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories.OfferRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offersRepo;

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("offer") Offer offer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "offers/create";
        }
        
        offersRepo.save(offer);

        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Offer offer = offersRepo.findById(id).get();

        model.addAttribute(offer);

        return "offers/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer offer, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "/offers/edit";
        }

        offersRepo.save(offer);

        return "redirect:/pizzas/" + offer.getPizza().getId();
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Offer offer = offersRepo.findById(id).get();

        offersRepo.delete(offer);
        
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }
    

}
