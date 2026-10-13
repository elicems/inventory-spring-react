package com.crud.training.model;

import com.crud.training.services.exceptions.DatabaseException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    public static boolean isAValidEmail(User user){
        if(!user.getEmail().trim().contains("@")){
            return false;
        }
        return true;
    }
}
