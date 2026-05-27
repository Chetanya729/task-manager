package com.project1.task_manager.exceptions;

import com.project1.task_manager.dt0.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(
            TaskNotFoundException ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .status(404)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .status(400)
                        .message(message)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .status(400)
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .status(500)
                        .message("Something went wrong. Please try again.")
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
