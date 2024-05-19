package com.nikhiln.learningNavigator.exception;

public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_MSG = "Resource Not Found";

    public NotFoundException() {
        super(DEFAULT_MSG);
    }

    public NotFoundException(String msg) {
        super(msg);
    }
    
}
