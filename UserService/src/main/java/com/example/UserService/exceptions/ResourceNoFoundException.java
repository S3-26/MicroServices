package com.example.UserService.exceptions;

public class ResourceNoFoundException extends RuntimeException {

    public ResourceNoFoundException()
    {
        super("Resource not found on server");
    }
    public ResourceNoFoundException(String message)
    {
        super(message);
    }
}
