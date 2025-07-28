package ecommerce.api.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductUpdateDTO implements ProductDTO {
    @Pattern(regexp = "^(?!\\s*$).+", message = "SKU cannot be blank")
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "SKU cannot be blank")
    private String sku;

    @DecimalMin(value = "0.0", message = "Price cannot be less than zero")
    private BigDecimal price;

    @PositiveOrZero
    private Integer stockQuantity;
}
