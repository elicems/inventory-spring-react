package com.crud.training.factory;

import com.crud.training.model.Product;

public class ProductFactory {
    public static Product build(){
        return new Product(0L,"",1,0.0,0.0);
    }
    public static Product build(Integer quantity){
        return new Product(0L,"",quantity,0.0,0.0);
    }
}
