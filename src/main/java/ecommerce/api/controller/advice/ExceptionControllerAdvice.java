package ecommerce.api.controller.advice;

import ecommerce.api.exception.ValidationErrorDetails;
import ecommerce.api.exception.ValidationException;
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
}
