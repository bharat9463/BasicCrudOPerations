package com.example.postgresql.exception;

public class IllegalArgumentException extends RuntimeException {
    private static final Long serialVersionUID=1L;
    public IllegalArgumentException(String message) {
        super(message);
    }
}
