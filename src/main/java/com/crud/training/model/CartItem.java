package com.crud.training.model;

import jakarta.persistence.Entity;

@Entity
public class CartItem {
    private Product product;
    private Cart shoppingCart;
}
