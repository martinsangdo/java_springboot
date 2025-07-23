package com.firstdata.main_pack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handles validation for @RequestParam, @PathVariable, @ModelAttribute
    // @ExceptionHandler(ConstraintViolationException.class)
    // public ResponseEntity<Map<String, List<String>>> handleConstraintViolation(ConstraintViolationException ex) {
    //     List<String> errors = ex.getConstraintViolations().stream()
    //             .map(ConstraintViolation::getMessage)
    //             .collect(Collectors.toList());
    //     return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    // }

    // // Handles validation for @RequestBody (e.g., DTOs)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
    //     List<String> errors = ex.getBindingResult().getFieldErrors()
    //             .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    //     return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    // }

    

    // private Map<String, List<String>> getErrorsMap(List<String> errors) {
    //     Map<String, List<String>> errorResponse = new HashMap<>();
    //     errorResponse.put("errors", errors);
    //     return errorResponse;
    // }
}

