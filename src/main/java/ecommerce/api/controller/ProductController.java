package ecommerce.api.controller;

import ecommerce.api.domain.Product;
import ecommerce.api.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> all() {
        return this.productService.all();
    }

    @GetMapping("/api/products/{id}")
    public Product findById(@PathVariable String id) {
        return this.productService.findById(id);
    }
}
