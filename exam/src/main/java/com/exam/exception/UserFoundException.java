package com.exam.exception;

public class UserFoundException extends Exception {


    public UserFoundException() {
        super("user with this username is not found in database");
    }

    public UserFoundException(String message) {
        super(message);
    }
}

