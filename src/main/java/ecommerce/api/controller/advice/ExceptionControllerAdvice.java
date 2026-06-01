package ecommerce.api.controller.advice;

import ecommerce.api.exception.ConflictException;
import ecommerce.api.exception.ValidationErrorDetails;
import ecommerce.api.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ValidationErrorDetails>> exceptionFieldValidationHandler(ValidationException ex) {
        List<ValidationErrorDetails> errorDetails = ex
            .getErrors()
            .stream()
            .map(
                e -> new ValidationErrorDetails(e.getField(), e.getDefaultMessage())
            ).toList();

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> exceptionFieldConflictHandler(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> exceptionFieldNotFoundHandler(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
