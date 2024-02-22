package com.bookMyShow_App.exception;

public class DataAlreadyExistException extends RuntimeException{

    String resourceName;
    String fieldName;
    String fieldValue;

    public DataAlreadyExistException(String resourceName,  String fieldName, String fieldValue) {
        super(String.format("'%s' already booked with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
