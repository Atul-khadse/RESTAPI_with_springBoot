package com.SpringToDatabase_JPA.SpringToDatabase_JPA.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
