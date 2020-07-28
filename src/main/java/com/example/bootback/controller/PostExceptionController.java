package com.example.bootback.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PostExceptionController {

    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody String noItemErrorHandler(NoSuchElementException e){
        // NoItemError noItem = new NoItemError();
        // noItem.setMessage("Item Not Exists!");
        return "Item Not Exists!";
    } 

    @ExceptionHandler(NumberFormatException.class)
    public @ResponseBody String pathFormatErrorHandler(NumberFormatException e){
        return "Invalid Path Variable!";
    }
    
}