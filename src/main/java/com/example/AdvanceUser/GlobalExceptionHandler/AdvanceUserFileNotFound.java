package com.example.AdvanceUser.GlobalExceptionHandler;

public class AdvanceUserFileNotFound extends RuntimeException {

    public AdvanceUserFileNotFound(String message){
        super(message);
    }

    public String toString(String s) {
        return ("Sorry, the record you seek does not exist");
    }
}
