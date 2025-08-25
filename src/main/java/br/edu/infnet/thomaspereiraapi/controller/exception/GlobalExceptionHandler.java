package br.edu.infnet.thomaspereiraapi.controller.exception;

import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidCustomerException;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerException;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put("timestamp:", LocalDateTime.now().toString());
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSellerException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidSellerException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp:", LocalDateTime.now().toString());
        errors.put("message:", ex.getMessage());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(SellerNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp:", LocalDateTime.now().toString());
        errors.put("message:", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCustomerException.class)
    public ResponseEntity<Map<String, String>> handleException(InvalidCustomerException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp:", LocalDateTime.now().toString());
        errors.put("message:", ex.getMessage());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("timestamp:", LocalDateTime.now().toString());
        errors.put("message:", ex.getMessage());
        errors.put("stackTrace:", ex.fillInStackTrace().toString());
        return new ResponseEntity<>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
