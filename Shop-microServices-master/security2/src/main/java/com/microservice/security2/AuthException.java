package com.microservice.security2;

public class AuthException extends RuntimeException{

    public AuthException(String message) {
        super(message);
    }
}
