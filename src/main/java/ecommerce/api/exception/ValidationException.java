package ecommerce.api.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        super(errors.toString());
        this.errors = errors;
    }
}
