package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException(String message) {
        super(message);
    }
}
