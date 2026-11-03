package com.crud.training.repositories;

import com.crud.training.model.CartItem;
import com.crud.training.model.pk.CartItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
