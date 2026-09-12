package com.crud.training.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(Object id) {
        super("Object not found: " + id);
    }
}
