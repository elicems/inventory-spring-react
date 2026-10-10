package com.crud.training.dto;

public record ProductFilterRequest(
        String description,
        Integer quantity,
        Double minPrice,
        Double maxPrice) {
}
