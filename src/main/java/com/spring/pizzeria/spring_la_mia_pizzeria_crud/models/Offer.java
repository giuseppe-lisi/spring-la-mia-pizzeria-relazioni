package com.spring.pizzeria.spring_la_mia_pizzeria_crud.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table( name = "offers" )
public class Offer {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "pizza_id", nullable = false)
    private Pizza pizza;

    @NotBlank(message = "Inserire un nome per")
    private String title;

    @NotNull(message = "Inserire una data di inizio")
    @PastOrPresent(message = "La data di inizio non può essere nel futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "Inserire una data di inizio")
    @Future(message = "La data di terminazione di un'offerta deve essere nel futuro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Integer getId() {
      return this.id;
    }
    public void setId(Integer value) {
      this.id = value;
    }

    public Pizza getPizza() {
      return this.pizza;
    }
    public void setPizza(Pizza value) {
      this.pizza = value;
    }

    public String getTitle() {
      return this.title;
    }
    public void setTitle(String value) {
      this.title = value;
    }

    public LocalDate getStartDate() {
      return this.startDate;
    }
    public void setStartDate(LocalDate value) {
      this.startDate = value;
    }

    public LocalDate getEndDate() {
      return this.endDate;
    }
    public void setEndDate(LocalDate value) {
      this.endDate = value;
    }
}
