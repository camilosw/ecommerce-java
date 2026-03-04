    # Phase 2: Data Persistence & Database Integration

**Duration:** 2-3 weeks

## Learning Objectives

* Master Spring Data JPA for database operations
* Understand entity relationships and JPA annotations
* Practice database schema design and migrations
* Learn connection pooling and transaction management

## Product Requirements

**Category Entity Specifications:**

* Category must have: ID (Long), name (String, max 50 chars), description (String, max 200 chars)
* Categories can have parent-child relationships (hierarchical)
* Category names must be unique at the same level in hierarchy
* Root categories have no parent

**Product-Category Relationship:**

* Each product must belong to exactly one category
* Categories can contain multiple products
* When a category is deleted, products must be reassigned or operation must be prevented

**Database Requirements:**

* System must support both H2 (for development) and PostgreSQL (for production)
* Database schema must be created automatically
* Application must load sample data on startup (at least 5 products, 3 categories)
* All database operations must be transactional

**Data Persistence Requirements:**

* Products and categories must be stored in database tables
* Foreign key relationships must be properly enforced
* System must support custom queries for finding products by category
* System must support finding categories by name pattern

**Performance Requirements:**

* Product listing must support finding products by category ID
* Category queries must support finding child categories of a parent
* All database queries must use proper indexing strategies

**Technical Implementation Goals:** Now figure out how to implement these database persistence requirements using Spring Data JPA.

## Acceptance Criteria

* Database tables are created automatically through JPA
* Entity relationships work correctly (Category ↔ Product)
* Custom repository queries function properly
* Sample data loads on application startup

## Testing Fundamentals (Introduced This Phase)

This phase introduces your first automated tests. Testing starts here — not at the end — because tests give you fast feedback that your JPA mappings and queries are actually correct, without needing to start the full server.

**What you'll learn:**

* JUnit 5 basics: `@Test`, `@BeforeEach`, `@AfterEach`, assertions (`assertEquals`, `assertThrows`, `assertThat`)
* Test naming conventions: method names should describe the scenario (e.g., `findByCategory_returnsMatchingProducts`)
* Repository testing with `@DataJpaTest`: spins up only the JPA layer (no web, no full context), uses an in-memory H2 database
* Writing your first tests: verify that entities persist correctly, custom queries return expected results, and constraints (e.g., unique names) are enforced

**Acceptance Criteria — Testing:**

* At least one `@DataJpaTest` class exists for `ProductRepository` and `CategoryRepository`
* Tests cover: save and find by ID, custom query methods, and relationship loading
* All tests pass with `./mvnw test`

## Advanced Features

* Implement soft deletion for products
* Add audit fields (created_date, modified_date)
* Create database indexes for performance optimization
