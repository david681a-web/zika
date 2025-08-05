package com.example.padaria_paotorrado.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcepttionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>handleGenericException(Exception ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", ex.getMessage());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error","Erro interno no servidor");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);


    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>handleValidationException(MethodArgumentNotValidException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("Status", HttpStatus.BAD_REQUEST.value());
        body.put("error","Erro interno no servidor");

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        body.put("fieldErrors", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
