package com.spring.pizzeria.spring_la_mia_pizzeria_crud.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredients")
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany(mappedBy = "ingredients")
  private List<Pizza> pizzas;

  @NotBlank(message = "Ingrediente deve avere un nome")
  private String name;

  // get set
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer value) {
    this.id = value;
  }

  public List<Pizza> getPizzas() {
    return this.pizzas;
  }

  public void setPizzas(List<Pizza> value) {
    this.pizzas = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }
}
