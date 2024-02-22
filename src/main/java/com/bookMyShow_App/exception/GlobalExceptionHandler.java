package com.bookMyShow_App.exception;

import com.bookMyShow_App.payload.ExceptionInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionInfo> dataNotFoundExceptionHandler(DataNotFoundException ex){
        String message = ex.getMessage();

        ExceptionInfo exceptionInfo = new ExceptionInfo(message);

        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<ExceptionInfo> dataAlreadyExistExceptionHandler(DataAlreadyExistException ex){
        String message = ex.getMessage();

        ExceptionInfo exceptionInfo = new ExceptionInfo(message);

        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exceptionInfo);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionInfo> apiExceptionHandler(ApiException ex){
        String message = ex.getMessage();

        ExceptionInfo exceptionInfo = new ExceptionInfo(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionInfo);
    }

    @ExceptionHandler(LimitExceedException.class)
    public ResponseEntity<ExceptionInfo> limitExceedExceptionHandler(LimitExceedException ex){
        String message = ex.getMessage();

        ExceptionInfo exceptionInfo = new ExceptionInfo(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionInfo);
    }
}
