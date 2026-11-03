package com.crud.training.controller;

import com.crud.training.model.Cart;
import com.crud.training.model.CartItem;
import com.crud.training.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/cartItem/{name}")
    public ResponseEntity<Cart> findByName(@PathVariable String name){
        Cart cart = service.findByName(name);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Cart> insert(@RequestBody Cart cart){
        cart = service.insert(cart);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cart.getId()).toUri();
        return ResponseEntity.created(uri).body(cart);
    }

//    public ResponseEntity<Cart> updateQuantity(@PathVariable Long id, CartItem item){
//        item = service.update(id,item);
//    }



}
