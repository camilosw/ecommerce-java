package ecommerce.api.repository;

import ecommerce.api.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findById_whenCategoryExist_thenReturnCategory() {
        Category category = Category.builder().name("Category 1").description("Description").build();
        Category savedCategory = categoryRepository.save(category);

        Optional<Category> foundCategory = categoryRepository.findById(savedCategory.getId());
        assertThat(foundCategory).contains(savedCategory);
    }

    @Test
    public void findById_whenChildHasParent_thenReturnCorrectParent() {
        Category parentCategory = Category.builder()
                .name("Parent category")
                .description("Description")
                .build();
        Category savedParentCategory = categoryRepository.save(parentCategory);
        Category childCategory = Category.builder()
                .name("Child category")
                .description("Description")
                .parent(savedParentCategory)
                .build();
        Category savedChildCategory = categoryRepository.save(childCategory);

        Optional<Category> foundChildCategory = categoryRepository.findById(savedChildCategory.getId());

        assertThat(foundChildCategory.orElseThrow().getParent()).isEqualTo(savedParentCategory);
    }

    @Test
    public void findByNameContaining_whenCategoryExist_thenReturnCorrectCategory() {
        Category category1 = Category.builder().name("Category 1").description("Description").build();
        Category savedCategory1 = categoryRepository.save(category1);
        Category category2 = Category.builder().name("Another").description("Description").build();
        categoryRepository.save(category2);

        List<Category> foundCategory = categoryRepository.findByNameContaining("tegor");

        assertThat(foundCategory).contains(savedCategory1);
        assertThat(foundCategory).hasSize(1);
    }
}
