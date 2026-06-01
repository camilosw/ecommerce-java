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
| Category deletion safety (reassign/prevent) | ✅ Done (prevent deletion via `ConflictException` → 409) |
| H2 for dev / PostgreSQL for prod config | ✅ Done (Spring profiles: `application-dev.properties`, `application-prod.properties`) |
| Sample data on startup (5 products, 3 categories) | ✅ Done (`DataInitializer` via `CommandLineRunner`) |
| `@Transactional` on service/repository operations | ✅ Done (`ProductService` — class-level `readOnly=true`, method-level overrides on writes) |
| Custom query: find products by category | ✅ Done (`findByCategoryId`) |
| Custom query: find categories by name pattern | ✅ Done (`findByNameContaining`) |
| Indexes for performance | ❌ Not done |
| Soft deletion for products | ❌ Not done |
| Audit fields (created_date, modified_date) | ❌ Not done |

---

### Key Concepts Applied This Session

- **ACID atomicity**: a transaction executes completely or rolls back entirely
- **Persistence context / Session**: JPA's in-memory tracking layer, tied to an active transaction
- **`LazyInitializationException`**: accessing a lazy-loaded relationship after the Session closes throws at runtime
- **`@Transactional(readOnly = true)`**: skips dirty checking and allows DB optimizations; use on read-only methods
- **Class-level + method-level `@Transactional` pattern**: class default is `readOnly=true`, write methods override with `@Transactional`
- **Dirty checking**: JPA auto-flushes changes to managed entities at commit — explicit `save()` not always needed, but preferred when returning the entity (ensures audit fields like `modified_date` are reflected in the returned object)
- **Spring vs Jakarta `@Transactional`**: always use `org.springframework.transaction.annotation.Transactional` in Spring Boot — supports `readOnly` and other Spring-specific options
- **Spring Profiles**: `application-{profile}.properties` files load per environment; `spring.profiles.active=dev` sets default; `SPRING_PROFILES_ACTIVE` env var overrides
- **Environment variable injection**: `${DB_PASSWORD}` syntax in properties reads from env vars — never hardcode secrets in prod config
- **`@Profile("dev")`**: conditionally registers a bean only when the specified profile is active (e.g., `DataInitializer` only in dev)
- **`ddl-auto` strategy**: `create` for dev (recreate schema each start), `validate` for prod (fail-fast if schema mismatches entities)
- **FK constraint violation**: DB enforces referential integrity — deleting a referenced row throws a constraint error, not a silent no-op
- **`ConflictException` → 409 Conflict**: custom exception for business-rule conflicts (e.g., category has products); handled globally via `@RestControllerAdvice`
- **`existsBy` queries**: more efficient than `countBy` when you only need a boolean check — stops at the first match
- **`@RequestMapping` at class level**: extracts common URL prefix so endpoint methods use relative paths
- **`@ResponseStatus(HttpStatus.NO_CONTENT)`**: returns 204 for DELETE — signals success with no body

### Key Concepts Still Remaining

- Column constraints (`@Column(length=50)`)
- Indexes (`@Index` on `@Table`)
- Audit fields (`@CreatedDate`, `@LastModifiedDate`)
- Soft deletion for products
