package com.cleber.diarioGlicemia.config;

import com.cleber.diarioGlicemia.exceptions.UserNameOuPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UserNameOuPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(UserNameOuPasswordInvalidException ex) {

        return ex.getMessage();
    }
}
