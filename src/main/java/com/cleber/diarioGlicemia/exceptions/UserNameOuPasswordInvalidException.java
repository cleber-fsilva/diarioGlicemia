package com.cleber.diarioGlicemia.exceptions;

public class UserNameOuPasswordInvalidException extends RuntimeException{

    public UserNameOuPasswordInvalidException(String message) {
        super(message);
    }
}
