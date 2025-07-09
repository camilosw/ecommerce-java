package ecommerce.api.service;

import ecommerce.api.domain.Product;
import ecommerce.api.exception.ValidationException;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>(
        List.of(
            new Product("1", "Product 1", "xyz", new BigDecimal("10.5"), 8)
        )
    );

    public List<Product> all() {
        return products;
    }

    public Optional<Product> findById(@NonNull String id) {
        return products
            .stream()
            .filter(p -> Objects.equals(p.getId(), id))
            .findFirst();
    }

    public Product addProduct(@NonNull Product product) {
        validateProduct(product);
        product.setId(UUID.randomUUID().toString());
        products.add(product);
        System.out.println("New product 4" + product);
        return product;
    }

    private void validateProduct(@NonNull Product product) {
        List<FieldError> errors = new ArrayList<>();

        boolean isSkuDuplicated = products
            .stream()
            .anyMatch(
                p -> p.getSku().equalsIgnoreCase(product.getSku())
            );

        if (isSkuDuplicated) {
            errors.add(new FieldError("Product", "name", "SKU must be unique"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
