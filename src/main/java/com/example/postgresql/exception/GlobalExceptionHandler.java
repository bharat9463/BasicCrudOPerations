package com.example.postgresql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    //handle specific
    // global exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException exception, WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnableToSaveException.class)
    public ResponseEntity<?> handleUnableToSolveException(UnableToSaveException exception, WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExceptionException(IllegalArgumentException exception, WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
