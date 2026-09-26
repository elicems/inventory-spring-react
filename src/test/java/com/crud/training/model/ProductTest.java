package com.crud.training.model;

import com.crud.training.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void shouldBeTrueWhenQuantityIsPositive(){
        var product = ProductFactory.build(1);
        Assertions.assertTrue(product.quantityIsGraterThanZero());
    }
    @Test
    void shouldBeFalseWhenQuantityIsNegative(){
        var product = ProductFactory.build(-2);
        Assertions.assertFalse(product.quantityIsGraterThanZero());
    }
    @Test
    void shouldBeFalseWhenQuantityIsZero(){
        var product = ProductFactory.build(0);
        Assertions.assertFalse(product.quantityIsGraterThanZero());
    }

}
