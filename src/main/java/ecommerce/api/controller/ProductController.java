package ecommerce.api.controller;

import ecommerce.api.domain.Product;
import ecommerce.api.domain.ProductCreateDTO;
import ecommerce.api.domain.ProductUpdateDTO;
import ecommerce.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Product findById(@PathVariable int id) {
        return this.productService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody ProductCreateDTO product) {
        return this.productService.add(product);
    }

    @PatchMapping("/api/products/{id}")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody ProductUpdateDTO product) {
        return this.productService.updateProduct(id, product);
    }

    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        this.productService.deleteProduct(id);
    }
}
