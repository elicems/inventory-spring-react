package com.crud.training.controller;

import com.crud.training.model.Product;
import com.crud.training.services.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@CrossOrigin()
@Tag(name = "Inventory",description = "Inventory control")
public class ProductController {
    private final ProductServices services;

    public ProductController(ProductServices services) {
        this.services = services;
    }

    @GetMapping
    @Operation(summary = "Show all products",description = "Show all products which registered in the database")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Show a products by id",description = "Show only one product with a informed id")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = services.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    @Operation(summary = "Register a product",description = "Register a product in database")
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = services.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a product",description = "Delete one product with the informed id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value ="/{id}")
    @Operation(summary = "Update one product",description = "Update one product with informed id")
    public ResponseEntity<Product> update(@PathVariable Long id,@RequestBody Product p){
        p = services.update(id,p);
        return ResponseEntity.ok().body(p);
    }
}
