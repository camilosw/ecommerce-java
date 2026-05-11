package ecommerce.api.repository;

import ecommerce.api.domain.Category;
import ecommerce.api.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById_whenProductExists_thenReturnProduct() {
        Product product = Product.builder().sku("123").name("Test product").price(new BigDecimal("5.0")).stockQuantity(10).build();
        Product savedProduct = productRepository.save(product);
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct).contains(savedProduct);
    }

    @Test
    public void findById_whenProductDoesNotExist_thenReturnEmptyOptional() {
        Optional<Product> foundProduct = productRepository.findById(999L);

        assertThat(foundProduct).isEmpty();
    }

    @Test
    public void findById_whenSavedWithCategory_thenProductHasCategory() {
        Category category = Category.builder().name("Category").build();
        Category savedCategory = categoryRepository.save(category);
        Product product = Product.builder()
                .sku("123")
                .name("Test product")
                .price(new BigDecimal("5.0"))
                .stockQuantity(10)
                .category(savedCategory)
                .build();
        Product savedProduct = productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct.orElseThrow().getCategory()).isEqualTo(savedCategory);
    }

    @Test
    public void findByCategoryId_whenProductsExist_thenReturnAllProductsInCategory() {
        Category category = Category.builder().name("Category").build();
        Category savedCategory = categoryRepository.save(category);
        Product product1 = Product.builder()
                .sku("123")
                .name("Test product")
                .stockQuantity(1)
                .price(new BigDecimal(5.0))
                .category(savedCategory)
                .build();
        Product savedProduct1 = productRepository.save(product1);
        Product product2 = Product.builder()
                .sku("456")
                .name("Test product")
                .stockQuantity(1)
                .price(new BigDecimal(5.0))
                .category(savedCategory)
                .build();
        Product savedProduct2 = productRepository.save(product2);

        List<Product> foundProducts = productRepository.findByCategoryId(savedCategory.getId());
        assertThat(foundProducts).containsExactlyInAnyOrder(savedProduct1, savedProduct2);
        assertThat(foundProducts).hasSize(2);
    }
}
