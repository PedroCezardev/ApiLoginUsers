package com.apilogin.demo.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException() {
        super("Este usuario nao foi encontrado.");
    }
    
}
