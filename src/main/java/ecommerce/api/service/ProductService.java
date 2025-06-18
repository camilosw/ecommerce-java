package ecommerce.api.service;

import ecommerce.api.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    public List<Product> all() {
        return List.of(
                new Product("1", "Product 1", "xyz", new BigDecimal("10.5"), 8)
        );
    }

    public Product findById(String id) {
        return new Product("1", "Product 1", "xyz", new BigDecimal("10.5"), 8);
    }

    public void addProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        System.out.println("New product " + product);
    }
}
