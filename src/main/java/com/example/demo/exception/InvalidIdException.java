package com.example.demo.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String message) {
        super(message);
    }
}
