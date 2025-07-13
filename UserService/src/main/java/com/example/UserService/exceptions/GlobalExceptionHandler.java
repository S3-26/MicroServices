package com.example.UserService.exceptions;

import com.example.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNoFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNoFoundException resourceNoFoundException)
    {
      ApiResponse response=  ApiResponse.builder().message(resourceNoFoundException.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
