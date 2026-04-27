package com.spring.pizzeria.spring_la_mia_pizzeria_crud.models;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

  // costruttore vuoto senza argomenti obbligatorio
  public Pizza() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  @NotBlank(message = "Name cannot be empty or shorter than 3 characters")
  @Size(min = 3, message = "Inserisci almeno 3 caratteri")
  private String name;

  @Lob
  @Column(nullable = false, columnDefinition = "TEXT")
  @NotBlank(message = "Inserisci una descrizione")
  private String description;

  private String img;

  @Column(precision = 10, scale = 2)
  @Min(value = 0, message = "Il prezzo deve essere positivo")
  private BigDecimal price;

  @OneToMany( mappedBy = "pizza", cascade = CascadeType.REMOVE )
  private List<Offer> offers;

  @ManyToMany
  @JoinTable( name = "pizza_ingredient",
    joinColumns = @JoinColumn( name = "pizza_id" ),
    inverseJoinColumns = @JoinColumn( name = "ingredient_id" ) 
  )
  private List<Ingredient> ingredients;

  // getter e setter
  public int getId() {
    return this.id;
  }

  public void setId(int value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  public String getImg() {
    return this.img;
  }

  public void setImg(String value) {
    this.img = value;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal value) {
    this.price = value;
  }
  
  public List<Offer> getOffers() {
    return offers;
  }
  
  public void setOffers(List<Offer> offers) {
    this.offers = offers;
  }
  
  public List<Ingredient> getIngredients() {
    return ingredients;
  }
  
  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  // override metodo toString
  @Override
  public String toString() {
    return String.format("%s - € %.2f", name, price);
  }
}
