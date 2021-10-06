package com.example.postgresql.exception;

public class UnableToSaveException extends RuntimeException{
    private static final Long serialVersionUID=1L;
    public UnableToSaveException(String message) {
        super(message);
    }

}
