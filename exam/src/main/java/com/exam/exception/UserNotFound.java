package com.exam.exception;

public class UserNotFound  extends Exception{


    public UserNotFound(){
        super("user with this username is already exist in DB ! try with another one");
    }
    public UserNotFound(String message){
        super(message);
    }

}
