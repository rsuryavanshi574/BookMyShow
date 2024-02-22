package com.bookMyShow_App.exception;

public class LimitExceedException extends RuntimeException{

    public LimitExceedException(String message) {
        super(message);
    }
}
