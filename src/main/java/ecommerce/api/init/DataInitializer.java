package ecommerce.api.init;

import ecommerce.api.domain.Category;
import ecommerce.api.domain.Product;
import ecommerce.api.repository.CategoryRepository;
import ecommerce.api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DataInitializer(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = Category.builder().name("Category 1").build();
        Category category2 = Category.builder().name("Category 2").build();
        Category category3 = Category.builder().name("Category 3").build();
        List<Category> savedCategories = categoryRepository.saveAll(List.of(category1, category2, category3));

        Product product1 = Product.builder().name("Product 1").price(new BigDecimal(10)).sku("abc").stockQuantity(1).category(savedCategories.getFirst()).build();
        Product product2 = Product.builder().name("Product 2").price(new BigDecimal(20)).sku("def").stockQuantity(2).category(savedCategories.getLast()).build();
        Product product3 = Product.builder().name("Product 3").price(new BigDecimal(30)).sku("ghi").stockQuantity(3).category(savedCategories.getFirst()).build();
        Product product4 = Product.builder().name("Product 4").price(new BigDecimal(40)).sku("jkl").stockQuantity(4).category(savedCategories.getLast()).build();
        Product product5 = Product.builder().name("Product 5").price(new BigDecimal(50)).sku("mno").stockQuantity(5).category(savedCategories.getFirst()).build();
        productRepository.saveAll(List.of(product1, product2, product3, product4, product5));
    }
}
