package com.alib.gateway;

public class UnauthorizedException extends RuntimeException{
    UnauthorizedException(String message) {
        super(message);
    }

}
