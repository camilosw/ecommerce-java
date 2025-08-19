package ecommerce.api.service;

import ecommerce.api.domain.Product;
import ecommerce.api.domain.ProductCreateDTO;
import ecommerce.api.domain.ProductUpdateDTO;
import ecommerce.api.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> all() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public Product add(@NonNull ProductCreateDTO productCreateDTO) {
        Product product = Product
            .builder()
            .name(productCreateDTO.getName())
            .sku(productCreateDTO.getSku())
            .price(productCreateDTO.getPrice())
            .stockQuantity(productCreateDTO.getStockQuantity())
            .build();

        Integer generatedId = productRepository.add(product);
        product.setId(generatedId);
        
        System.out.println("New product " + product);
        return product;
    }

    public Product updateProduct(int id, @NonNull ProductUpdateDTO productUpdateDTO) {
        Product product = new Product();
        if (productUpdateDTO.getName() != null) {
            product.setName(productUpdateDTO.getName());
        }
        if (productUpdateDTO.getSku() != null) {
            product.setSku(productUpdateDTO.getSku());
        }
        if (productUpdateDTO.getPrice() != null) {
            product.setPrice(productUpdateDTO.getPrice());
        }
        if (productUpdateDTO.getStockQuantity() != null) {
            product.setStockQuantity(productUpdateDTO.getStockQuantity());
        }

        boolean updated = productRepository.update(id, product);
        if (!updated) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        return product;
    }

    public void deleteProduct(int id) {
        productRepository.delete(id);
    }
}
