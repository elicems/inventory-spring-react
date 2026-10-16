package com.crud.training.model.pk;

import com.crud.training.model.Cart;
import com.crud.training.model.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CartItemPK {

    @ManyToOne
    @JoinColumn(name = "cart_item")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
