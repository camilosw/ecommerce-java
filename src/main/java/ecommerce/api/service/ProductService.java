package ecommerce.api.service;

import ecommerce.api.domain.Product;
import ecommerce.api.domain.ProductCreateDTO;
import ecommerce.api.domain.ProductDTO;
import ecommerce.api.domain.ProductUpdateDTO;
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

    public Product addProduct(@NonNull ProductCreateDTO productCreateDTO) {
        validateProduct(productCreateDTO);
        Product product = Product
            .builder()
            .id(UUID.randomUUID().toString())
            .name(productCreateDTO.getName())
            .sku(productCreateDTO.getSku())
            .price(productCreateDTO.getPrice())
            .stockQuantity(productCreateDTO.getStockQuantity())
            .build();

        products.add(product);
        System.out.println("New product " + product);
        return product;
    }

    public Product updateProduct(@NonNull String id, @NonNull ProductUpdateDTO productUpdateDTO) {
        validateProduct(productUpdateDTO);
        Optional<Product> currentProductOptional = products
            .stream()
            .filter(p -> Objects.equals(p.getId(), id))
            .findFirst();

        currentProductOptional.orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        Product currentProduct = currentProductOptional.get();
        if (productUpdateDTO.getName() != null) {
            currentProduct.setName(productUpdateDTO.getName());
        }
        if (productUpdateDTO.getSku() != null) {
            currentProduct.setSku(productUpdateDTO.getSku());
        }
        if (productUpdateDTO.getPrice() != null) {
            currentProduct.setPrice(productUpdateDTO.getPrice());
        }
        if (productUpdateDTO.getStockQuantity() != null) {
            currentProduct.setStockQuantity(productUpdateDTO.getStockQuantity());
        }

        products.replaceAll(p -> p.getId().equals(currentProduct.getId()) ? currentProduct : p);
        return currentProduct;
    }

    public void deleteProduct(@NonNull String id) {
        products.removeIf(p -> Objects.equals(p.getId(), id));
    }

    private void validateProduct(@NonNull ProductDTO product) {
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
