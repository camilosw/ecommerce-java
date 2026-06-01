package ecommerce.api.service;

import ecommerce.api.exception.ConflictException;
import ecommerce.api.repository.CategoryRepository;
import ecommerce.api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        boolean categoryExists = categoryRepository.existsById(id);
        if (!categoryExists) {
            throw new EntityNotFoundException("Category with id " + id + " does not exist");
        }

        boolean categoryHasProducts = this.productRepository.existsByCategoryId(id);
        if (categoryHasProducts) {
            throw new ConflictException("Category has products");
        }

        this.categoryRepository.deleteById(id);
    }
}
