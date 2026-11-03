package com.crud.training.services;

import com.crud.training.model.Cart;
import com.crud.training.model.CartItem;
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
    public Cart findByName(String name){
        Optional<Cart> obj = repo.findByItems(name);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Product not found"));
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
    public Cart update(Long id,CartItem newItem){
        try{
            Cart cart1 = repo.getReferenceById(id);
            updateData(cart1,newItem);
            return repo.save(cart1);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(id);
        }
    }
    private void updateData(Cart cart,CartItem newItem){
        List<CartItem> list = cart.getItems();
        for(CartItem item: list){
            item.setQuantity(newItem.getQuantity());
        }
    }


}
