package ecommerce.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorDetails {
    private String field;
    private String message;
}
