package com.spring.pizzeria.spring_la_mia_pizzeria_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Pizza;
import java.util.List;


// interfaccia repository che estendendo JpaRepository ci fornirà in automatico tutti i metodi
// base per eseguire operazioni crud... mezzo una figata. 
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    // per sapere come eseguire questi metodi CRUD darà per scontato che abbiamo definito
    // i nostri oggetti rappresentativi delle entità del db seguendo la specifica JPA
    // starà poi ad hibernate fare il grosso del lavoro dopo aver invocato un metodo di questa
    // interfaccia, ovvero, scrivere la query string corretta ed interfacciarsi con JDBC che andrà a 
    // recuperare effettivamente il dato dal db


    // metodo custom per cercare in base al nome
    List<Pizza> findByNameContaining(String name);
}
