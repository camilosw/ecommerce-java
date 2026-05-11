package ecommerce.api.service;

import ecommerce.api.domain.Product;
import ecommerce.api.domain.ProductCreateDTO;
import ecommerce.api.domain.ProductUpdateDTO;
import ecommerce.api.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
