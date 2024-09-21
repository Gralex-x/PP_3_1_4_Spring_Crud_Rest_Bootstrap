package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
