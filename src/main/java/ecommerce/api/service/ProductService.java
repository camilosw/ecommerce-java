package ecommerce.api.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<String> all() {
        return List.of("Product 1", "Product 3");
    }
}
