package com.bookMyShow_App.exception;

public class DataNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    String fieldValue;


    public DataNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
