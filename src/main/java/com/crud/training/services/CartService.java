package com.crud.training.services;

import com.crud.training.model.Cart;
import com.crud.training.repositories.CartRepository;
import com.crud.training.services.exceptions.DatabaseException;
import com.crud.training.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository repo;

    public CartService(CartRepository repo){
        this.repo = repo;
    }

    public List<Cart> findAll(){
        return repo.findAll();
    }
    public Cart findById(Long id){
        Optional<Cart> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }
    public Cart insert(Cart newCart){
        if(newCart == null){
            return null;
        }
        return repo.save(newCart);
    }
    public void delete(Long id){
        try {
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
