package com.crud.training.services;

import com.crud.training.model.Product;
import com.crud.training.repositories.ProductRepository;
import com.crud.training.services.exceptions.DatabaseException;
import com.crud.training.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Product insert(Product newProduct){
        if(newProduct == null || newProduct.getDescription() == null){
                throw new IllegalArgumentException("Product cannot be null");
        }
        Optional<Product> existingProductOpt = findProductInInventory(newProduct);
        if(existingProductOpt.isPresent()){
            Product existingProduct = existingProductOpt.get();

            existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
            repo.save(existingProduct);
        }else {
            return repo.save(newProduct);
        }
        return null;
    }
    public void delete(Long id){
        try {
            repo.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ObjectNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public Product update(Long id,Product newProduct){
        try {
            Product p = repo.getReferenceById(id);
            updateData(p,newProduct);
            return repo.save(p);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(id);
        }
    }
    private Optional<Product> findProductInInventory(Product obj){
        if(obj == null || obj.getDescription() == null){
            return null;
        }
        List<Product> inventory = findAll();
        String newProductDescription = obj.getDescription().replaceAll(" ","");
        Optional<Product> found = inventory.stream().
                filter(p -> p.getDescription().replaceAll(" ","").
                        equalsIgnoreCase(newProductDescription)).findFirst();
        return found;
    }
    private void updateData(Product product,Product newProduct){
        product.setDescription(newProduct.getDescription());
        product.setQuantity(newProduct.getQuantity());
    }
}
