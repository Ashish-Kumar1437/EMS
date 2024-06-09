package com.Product.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleGlobalException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
