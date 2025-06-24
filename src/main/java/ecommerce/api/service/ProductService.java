package ecommerce.api.service;

import ecommerce.api.domain.Product;
import lombok.NonNull;
import org.springframework.stereotype.Service;

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

    public void addProduct(@NonNull Product product) {
        product.setId(UUID.randomUUID().toString());
        products.add(product);
        System.out.println("New product " + product);
    }
}
