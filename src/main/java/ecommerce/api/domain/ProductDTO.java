package ecommerce.api.domain;

import java.math.BigDecimal;

public interface ProductDTO {
    String getName();
    String getSku();
    BigDecimal getPrice();
    Integer getStockQuantity();
}
