package com.crud.training.controller;

import com.crud.training.model.Product;
import com.crud.training.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@CrossOrigin()
public class ProductController {
    private final ProductServices services;

    public ProductController(ProductServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = services.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = services.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value ="/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,@RequestBody Product p){
        p = services.update(id,p);
        return ResponseEntity.ok().body(p);
    }
}
