package com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Ingredient;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
    
}
