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

    public Optional<Product> findById(Long id) {
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

        Product savedProduct = productRepository.save(product);
        product.setId(savedProduct.getId());
        
        System.out.println("New product " + product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, @NonNull ProductUpdateDTO productUpdateDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return optionalProduct;
        }
        Product product = optionalProduct.get();

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

        Product savedProduct = productRepository.save(product);
        return Optional.of(savedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
