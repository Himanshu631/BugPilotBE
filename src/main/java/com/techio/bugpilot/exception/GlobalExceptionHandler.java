package com.techio.bugpilot.exception;

import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse<String>> handleInvalidJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                GeneralUtility.failure("Invalid JSON format: " + ex.getMostSpecificCause().getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<String>> handleGeneralError(Exception ex) {
        return ResponseEntity.internalServerError().body(
                GeneralUtility.failure("Internal server error: " + ex.getMessage())
        );
    }
}

