package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.ResponseEntity.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(RuntimeException ex) {

        String msg = ex.getMessage().toLowerCase();

        if (msg.contains("already exists")) {
            return Response.conflict(ex.getMessage(), null);
        }

        if (msg.contains("invalid credentials")) {
            return Response.unauthorized(ex.getMessage());
        }

        if (msg.contains("not found")) { // For to do not found
            return Response.notfound(ex.getMessage(), null); // Or create Response.notFound() if you want 404
        }

        return Response.badRequest(ex.getMessage(), null);
    }
}
