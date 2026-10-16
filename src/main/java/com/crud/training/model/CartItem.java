package com.crud.training.model;

import com.crud.training.model.pk.CartItemPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {
    @EmbeddedId
    private CartItemPK pk = new CartItemPK();
    private Integer quantity;
    private Double price;

    public CartItem(Cart cart,Product product,Integer quantity,Double price){
        pk.setCart(cart);
        pk.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Cart getCart(){
        return pk.getCart();
    }
    public void setCart(Cart cart){
        pk.setCart(cart);
    }

    public Product getProduct(){
        return pk.getProduct();
    }
    public void setProduct(Product product){
        pk.setProduct(product);
    }
}
