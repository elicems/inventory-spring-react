package com.crud.training.services;

import com.crud.training.model.CartItem;
import com.crud.training.repositories.CartItemRepository;
import com.crud.training.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository repo;

    public CartItemService(CartItemRepository repo) {
        this.repo = repo;
    }

    public List<CartItem> findAll(){
        return repo.findAll();
    }

    public CartItem findById(Long id){
        Optional<CartItem> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }
}
