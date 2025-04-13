package edu.vwcc.guestbook.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice(basePackages = "edu.vwcc.guestbook.api")  // Restrict this advice to only apply to RestController classes (not Thymeleaf pages served)
@Order(1)  // Higher priority to ensure it intercepts API requests first
public class ApiExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(Exception ex, WebRequest request) {
    	
        // Important: this Controller only handles API requests (check Accept header)
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("timestamp", LocalDateTime.now());
            errorBody.put("status", HttpStatus.NOT_FOUND.value());
            errorBody.put("error", "Not Found");
            errorBody.put("message", ex.getMessage());
            return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
        }
        
        // For non-API requests, return null to let the default error handler take over
        return null;
    }
}