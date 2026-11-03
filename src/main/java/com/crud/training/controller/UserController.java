package com.crud.training.controller;

import com.crud.training.model.User;
import com.crud.training.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Show all users", description = "Show all users registered in database")
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findALl();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Show the user by id", description = "Show only the user by informed id")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    @Operation(summary = "Insert a new users", description = "Insert a new user in the database")
    public ResponseEntity<User> insert(@RequestBody User user){
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delte a user", description = "Delete a user by informed id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update the user data", description = "Update the user data by informed id")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        user = service.update(id,user);
        return ResponseEntity.ok().body(user);
    }
}
