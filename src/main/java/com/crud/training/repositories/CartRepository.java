package com.crud.training.repositories;

import com.crud.training.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    public Optional<Cart> findByItems(String name);
}
