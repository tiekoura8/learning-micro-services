package com.tiekoura.learningmicroservices.exception;

public class CustomerWithEmailAlreadyExist extends RuntimeException {
    public CustomerWithEmailAlreadyExist(String message) {
        super(message);
    }
}
