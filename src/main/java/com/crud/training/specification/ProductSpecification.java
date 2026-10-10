package com.crud.training.specification;

import com.crud.training.dto.ProductFilterRequest;
import com.crud.training.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> filter(ProductFilterRequest product){
        return Specification.allOf(
                descriptionContains(product.description()),
                quantityLowerThan(product.quantity()),
                unitPriceBetween(product.minPrice(), product.maxPrice())
        );
    }
    private static Specification<Product> descriptionContains(String description){
        return ((root, query, cb) -> {
            if(description == null || description.isBlank()){
                return null;
            }

            return cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
        });
    }
    private static Specification<Product> quantityLowerThan(Integer quantity){
        return (root, query, cb) -> {
            if(quantity == null){
                return null;
            }

            return cb.lessThan(root.get("quantity"),quantity);
        };
    }
    private static Specification<Product> unitPriceBetween(Double minPrice,Double maxPrice){
        return (root, query, criteriaBuilder) -> {
            if(minPrice != null && maxPrice != null){
                return criteriaBuilder.between(root.get("unitPrice"),minPrice,maxPrice);
            }
            if(minPrice != null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("unitPrice"),minPrice);
            }
            if(maxPrice != null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("unitPrice"),maxPrice);
            }
            return null;
        };
    }
}
