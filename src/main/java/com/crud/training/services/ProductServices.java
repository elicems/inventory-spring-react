package com.crud.training.services;

import com.crud.training.model.Product;
import com.crud.training.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private final ProductRepository repo;

    public ProductServices(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll(){
        return repo.findAll();
    }
    public Product findById(Long id){
        Optional<Product> obj = repo.findById(id);
        return obj.get();
    }
}
