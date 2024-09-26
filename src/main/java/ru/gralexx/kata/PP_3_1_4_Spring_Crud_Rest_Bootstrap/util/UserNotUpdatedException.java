package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException(String message) {
        super(message);
    }
}
