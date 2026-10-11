package com.crud.training.services;

import com.crud.training.model.Product;
import com.crud.training.model.User;
import com.crud.training.repositories.UserRepository;
import com.crud.training.services.exceptions.DatabaseException;
import com.crud.training.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }
    public List<User> findALl(){
        return repo.findAll();
    }
    public User findById(Long id){
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(id));
    }
    public User insert(User newUser){
        if(newUser == null || newUser.getEmail().isBlank()){
            throw new DatabaseException("The user cannot be null");
        }
        return repo.save(newUser);
    }
    public void delete(Long id){
        try{
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ObjectNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public User update(Long id,User newUser){
        try{
            User u = repo.getReferenceById(id);
            updateData(u,newUser);
            return repo.save(u);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(id);
        }
    }
    private void updateData(User user,User newUser){
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }
}
