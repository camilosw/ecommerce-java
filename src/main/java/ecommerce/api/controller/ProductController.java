package ecommerce.api.controller;

import ecommerce.api.domain.Product;
import ecommerce.api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

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
        return this.productService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/products")
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }
}
