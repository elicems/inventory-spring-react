package com.crud.training.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double totalValue;

    public Boolean quantityIsGraterThanZero(){
        return quantity >0;
    }
    public Integer sumQuantity(Integer newQuantity){
        return this.quantity + newQuantity;
    }
    public Boolean unitPriceIsPositive(){
        return unitPrice > 0.0;
    }
    public Double multiTotalValue(){
        if(unitPriceIsPositive() && quantityIsGraterThanZero()){
            return unitPrice * quantity;
        }
        else {
            return 0.0;
        }
    }
}
