package com.crud.training.controller;

import com.crud.training.model.Cart;
import com.crud.training.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/carrinho")
public class CartController {
    private final CartService service;

    public CartController(CartService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll(){
        List<Cart> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/cartItem/{id}")
    public ResponseEntity<Cart> findBy(@PathVariable Long id){
        Cart cart = service.findById(id);
        return ResponseEntity.ok().body(cart);
    }
}
