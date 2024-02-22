package com.bookMyShow_App.exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
