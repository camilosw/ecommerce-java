package ecommerce.api.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    @NotNull
    @DecimalMin(value = "0.0", message = "Price cannot be less than zero")
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer stockQuantity;
}
