# E-Commerce API — Completion Summary

## Phase 1 — COMPLETED ✅

### What Was Built
A REST API with in-memory storage for product management.

**Stack:** Java 21, Spring Boot, Spring Web, Lombok, Maven

**Package structure:**
```
ecommerce.api/
├── controller/ProductController.java
├── service/ProductService.java
├── domain/
│   ├── Product.java
│   ├── ProductCreateDTO.java
│   ├── ProductUpdateDTO.java
│   └── ProductDTO.java (interface)
└── exception/ValidationException.java
```

**Endpoints:**
- `GET /api/products` — list all
- `GET /api/products/{id}` — get by id (404 if not found)
- `POST /api/products` — create (201)
- `PATCH /api/products/{id}` — partial update
- `DELETE /api/products/{id}` — delete (204)

**Key decisions made:**
- `BigDecimal` for price precision
- UUID for product IDs
- `PATCH` over `PUT` for updates
- DTO pattern: separate create/update DTOs with shared `ProductDTO` interface
- Custom `ValidationException` with field-level errors
- Constructor injection throughout

**Current Product model:**
```java
Product {
    String id;           // UUID
    String name;         // max 100 chars
    String sku;          // unique
    BigDecimal price;    // positive
    Integer stockQuantity; // non-negative
}
```

**Key patterns mastered:**
- DTO pattern: separate create/update DTOs with shared `ProductDTO` interface
- Custom `ValidationException` with field-level errors
- Service layer separation (Controller → Service → Data)
- Constructor dependency injection throughout
- Builder pattern for object creation
- Bean validation annotations (`@DecimalMin`, `@PositiveOrZero`, `@Pattern`)
- In-memory data storage with `ArrayList`

**Student strengths demonstrated:**
- Strong independent research skills
- Professional-level architectural thinking
- Excellent grasp of REST API principles
- `BigDecimal` research for financial calculations
- Understanding of HTTP methods (PATCH vs PUT decision)
- Custom business logic validation (SKU uniqueness)

---

## Phase 2 — IN PROGRESS 🔄

### What Was Built So Far

Migrated from Spring JDBC (JdbcTemplate) to Spring Data JPA. Both `Product` and `Category` entities are fully mapped with JPA annotations and relationships.

**Package structure additions:**
```
ecommerce.api/
├── domain/
│   ├── Product.java         ← @Entity, @ManyToOne Category
│   └── Category.java        ← @Entity, self-referencing @ManyToOne/@OneToMany
└── repository/
    ├── ProductRepository.java   ← interface extends JpaRepository<Product, Long>
    └── CategoryRepository.java  ← interface extends JpaRepository<Category, Long>
src/test/
└── repository/
    ├── ProductRepositoryTest.java  ← @DataJpaTest, 3 tests
    └── CategoryRepositoryTest.java ← @DataJpaTest, 2 tests
```

**Product model (current):**
```java
Product {
    Long id;               // @GeneratedValue IDENTITY
    String name;
    String sku;
    BigDecimal price;
    Integer stockQuantity;
    Category category;     // @ManyToOne
}
```

**Category model:**
```java
Category {
    Long id;               // @GeneratedValue IDENTITY
    String name;
    String description;
    Category parent;       // @ManyToOne (self-reference)
    List<Category> children; // @OneToMany(mappedBy="parent"), excluded from toString/equals
}
```

**Key concepts applied:**
- `jakarta.persistence` annotations: `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`
- `mappedBy` to declare inverse side of bidirectional relationship
- `@ToString.Exclude` + `@EqualsAndHashCode.Exclude` to prevent infinite recursion with Lombok `@Data`
- Wrapper types (`Long`) for JPA IDs over primitives
- `JpaRepository<Entity, ID>` — Spring generates all CRUD implementations
- `save()` handles both INSERT and UPDATE
- `deleteById()` replaces manual SQL delete
- `updateProduct()` in service returns `Optional<Product>` — controller handles 404 via `orElseThrow()`
- `@DataJpaTest`: loads only JPA layer, uses in-memory H2, no web/service layer
- Test naming: `methodUnderTest_scenario_expectedOutcome`
- AssertJ: `assertThat(optional).contains(value)`, `.isEmpty()`, `.orElseThrow()`

---

### Phase 2 Checklist

| Requirement | Status |
|---|---|
| Products stored in database | ✅ Done (H2 via JPA) |
| Database schema created automatically | ✅ Done (JPA DDL auto) |
| Repository pattern implemented | ✅ Done |
| Spring Data JPA / `@Entity` annotations | ✅ Done |
| Category entity (id, name, description) | ✅ Done |
| Hierarchical categories (parent-child) | ✅ Done |
| Product belongs to one Category (FK) | ✅ Done |
| Repository tests with `@DataJpaTest` | ✅ Done (7 tests, all passing) |
| Category deletion safety (reassign/prevent) | ❌ Not done |
| H2 for dev / PostgreSQL for prod config | ❌ Not done |
| Sample data on startup (5 products, 3 categories) | ✅ Done (`DataInitializer` via `CommandLineRunner`) |
| `@Transactional` on service/repository operations | ❌ Not done |
| Custom query: find products by category | ✅ Done (`findByCategoryId`) |
| Custom query: find categories by name pattern | ✅ Done (`findByNameContaining`) |
| Indexes for performance | ❌ Not done |
| Soft deletion for products | ❌ Not done |
| Audit fields (created_date, modified_date) | ❌ Not done |

---

### Key Concepts Still Remaining

- JPQL / derived query methods for custom repository queries
- `@Transactional` annotation and transaction boundaries
- Spring profiles for H2 vs PostgreSQL (`application-dev.properties`, `application-prod.properties`)
- `CommandLineRunner` or `data.sql` for sample data seeding
- Column constraints (`@Column(length=50)`)
- Indexes (`@Index` on `@Table`)
