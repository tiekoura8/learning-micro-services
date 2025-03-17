package com.tiekoura.learningmicroservices.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
    public NoSuchCustomerExistsException(String message) {
        super(message);
    }
}
