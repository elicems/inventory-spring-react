package com.crud.training.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class Cart {
    private Long id;

    @OneToOne
    private User user;

}
