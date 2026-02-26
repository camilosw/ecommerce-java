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

Products are now persisted to a real database using Spring JDBC (JdbcTemplate), replacing the in-memory ArrayList from Phase 1.

**Approach chosen:** Spring JDBC (`JdbcTemplate`) — *Note: Phase 2 calls for Spring Data JPA; this is a lower-level alternative that achieves persistence but skips JPA/ORM entirely. This may need revisiting.*

**New files:**
```
ecommerce.api/
└── repository/ProductRepository.java   ← new; JdbcTemplate-based CRUD
resources/
└── schema.sql                          ← auto-runs on startup, creates product table
```

**Schema (`schema.sql`):**
```sql
CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    sku varchar(50) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL,
    stockQuantity INT NOT NULL
);
```

**Product model changes:**
- ID changed from `String` (UUID) → `int` (auto-increment, DB-generated)
- JPA/entity annotations: not yet added (plain POJO)

**Repository pattern:**
- `ProductRepository` handles all SQL via `JdbcTemplate`
- Uses `RowMapper` to map ResultSet → Product
- Uses `GeneratedKeyHolder` to retrieve the auto-generated ID after INSERT
- `ProductService` now delegates to `ProductRepository` (no more in-memory list)

---

### Phase 2 Checklist

| Requirement | Status |
|---|---|
| Products stored in database | ✅ Done (H2 via JdbcTemplate) |
| Database schema created automatically | ✅ Done (`schema.sql`) |
| Repository pattern implemented | ✅ Done |
| Spring Data JPA / `@Entity` annotations | ❌ Not done (using raw JDBC instead) |
| Category entity (id, name, description) | ❌ Not done |
| Hierarchical categories (parent-child) | ❌ Not done |
| Product belongs to one Category (FK) | ❌ Not done |
| Category deletion safety (reassign/prevent) | ❌ Not done |
| H2 for dev / PostgreSQL for prod config | ❌ Not done (H2 implicit, no profiles) |
| Sample data on startup (5 products, 3 categories) | ❌ Not done |
| `@Transactional` on service/repository operations | ❌ Not done |
| Custom query: find products by category | ❌ Not done |
| Custom query: find categories by name pattern | ❌ Not done |
| Indexes for performance | ❌ Not done |
| Soft deletion for products | ❌ Not done |
| Audit fields (created_date, modified_date) | ❌ Not done |
| Repository tests with `@DataJpaTest` | ❌ Not done |

---

### Key Concepts Still Remaining

- Spring Data JPA: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`
- `JpaRepository` interface (replaces manual SQL)
- `@Transactional` annotation and transaction boundaries
- Spring profiles for H2 vs PostgreSQL (`application-dev.properties`, `application-prod.properties`)
- `CommandLineRunner` or `data.sql` for sample data seeding
- JPQL / derived query methods for custom repository queries
- `@DataJpaTest` for slice testing
