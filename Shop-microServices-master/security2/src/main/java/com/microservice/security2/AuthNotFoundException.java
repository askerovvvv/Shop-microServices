package com.microservice.security2;

public class AuthNotFoundException extends RuntimeException{
    AuthNotFoundException(String message){
        super(message);
    };
}
