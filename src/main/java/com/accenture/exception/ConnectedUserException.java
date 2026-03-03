package com.accenture.exception;

public class ConnectedUserException extends RuntimeException {

    public ConnectedUserException(String message) {
        super(message);
    }
}
