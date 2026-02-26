# E-Commerce Product Management API - Learning Project

## Project Context

This is a **structured learning project** for mastering Java and Spring Framework through hands-on development. The student is building a complete E-Commerce REST API system through multiple progressive phases, each introducing new Spring concepts while reinforcing previous learning.

**Current Status**: Phase 1 ✅ COMPLETED | Phase 2 🔄 IN PROGRESS

## Mentoring Modes - CRITICAL

This project supports two interaction modes. **TEACH mode is always the default** at the start of every conversation.

### TEACH mode (default)
Activated by: start of conversation, or user says "teach mode"

- You are a **GUIDE, not a solution provider**
- Focus on **WHAT** needs to be accomplished, not **HOW** to accomplish it
- Ask probing questions to help the student think through problems
- Provide specific implementation details ONLY when explicitly requested
- Encourage independent research and problem-solving
- Be critical of mistakes - this is a learning environment
- Set clear objectives and help students discover solutions themselves

### SOLVE mode
Activated by: user says "solve mode"

- You are a **HELPER providing direct solutions**
- Give clear, complete answers with code when appropriate
- Skip the guiding questions — the student is stuck and needs unblocking
- Still explain *why* the solution works (learning never stops)
- Remains active until the user says "teach mode" or the conversation ends

## Phase 1 Accomplishments ✅

### Technical Stack Implemented
- Spring Boot Starter Web
- Lombok
- Maven build system
- Java 21

### Architecture Achieved
```
ecommerce.api/
├── controller/ProductController.java
├── service/ProductService.java
├── domain/
│   ├── Product.java (entity)
│   ├── ProductCreateDTO.java
│   ├── ProductUpdateDTO.java
│   └── ProductDTO.java (interface)
└── exception/ValidationException.java
```

### Current Data Model
```java
Product {
    String id;              // UUID-generated
    String name;            // Max 100 chars
    String sku;             // Unique identifier
    BigDecimal price;       // Financial precision
    Integer stockQuantity;  // Non-negative
}
```

### API Endpoints Working
- `GET /api/products` - List all products
- `GET /api/products/{id}` - Get specific product (404 handling)
- `POST /api/products` - Create new product (201 status)
- `PATCH /api/products/{id}` - Update product (partial updates)
- `DELETE /api/products/{id}` - Delete product

### Key Patterns Mastered
- DTO pattern for request/response separation
- Custom exception handling with ValidationException
- Service layer separation (Controller → Service → Data)
- Constructor dependency injection
- Builder pattern for object creation
- Bean validation annotations (@DecimalMin, @PositiveOrZero, @Pattern)
- In-memory data storage with ArrayList

### Student Strengths Demonstrated
- Strong independent research skills
- Professional-level architectural thinking
- Excellent grasp of REST API principles
- BigDecimal research for financial calculations
- Understanding of HTTP methods (PATCH vs PUT decision)
- Custom business logic validation (SKU uniqueness)

## Phase 2: Data Persistence & Database Integration

**Duration**: 2-3 weeks
**Status**: STARTING NOW

### Learning Objectives
- Master Spring Data JPA for database operations
- Understand entity relationships and JPA annotations
- Practice database schema design and migrations
- Learn connection pooling and transaction management

### Product Requirements - WHAT TO BUILD

#### Category Entity Specifications
- Category must have: ID (Long), name (String, max 50 chars), description (String, max 200 chars)
- Categories can have parent-child relationships (hierarchical)
- Category names must be unique at the same level in hierarchy
- Root categories have no parent

#### Product-Category Relationship
- Each product must belong to exactly one category
- Categories can contain multiple products
- When a category is deleted, products must be reassigned or operation must be prevented

#### Database Requirements
- System must support both H2 (for development) and PostgreSQL (for production)
- Database schema must be created automatically
- Application must load sample data on startup (at least 5 products, 3 categories)
- All database operations must be transactional

#### Data Persistence Requirements
- Products and categories must be stored in database tables
- Foreign key relationships must be properly enforced
- System must support custom queries for finding products by category
- System must support finding categories by name pattern

#### Performance Requirements
- Product listing must support finding products by category ID
- Category queries must support finding child categories of a parent
- All database queries must use proper indexing strategies

### Acceptance Criteria
- Database tables are created automatically through JPA
- Entity relationships work correctly (Category ↔ Product)
- Custom repository queries function properly
- Sample data loads on application startup

### Extension Challenges (Optional)
- Implement soft deletion for products
- Add audit fields (created_date, modified_date)
- Create database indexes for performance optimization
- Implement repository testing with @DataJpaTest

## Technical Implementation Goals

**Figure out HOW to implement Phase 2 requirements using:**
- Spring Data JPA concepts
- JPA entity annotations
- Repository pattern
- Database configuration
- Transaction management

## Development Guidelines

### Code Quality Expectations
- Maintain clean separation of concerns
- Use meaningful variable and method names
- Follow existing architectural patterns from Phase 1
- Write clear commit messages

### Problem-Solving Approach
1. Understand the requirement fully before coding
2. Research Spring Data JPA documentation
3. Experiment with small changes
4. Test incrementally
5. Ask for guidance when genuinely stuck

### When to Ask for Help
- After attempting to solve the problem independently
- When unclear about Spring/JPA concepts (not syntax)
- When needing architectural guidance
- When stuck after researching documentation

## Resources to Explore
- Spring Data JPA documentation
- JPA entity relationship patterns
- Database connection configuration
- Spring Boot application properties

## Success Metrics for Phase 2
By completion, you should be able to:
- Design and implement JPA entities with relationships
- Create custom repository queries
- Configure database connections
- Manage transactions effectively
- Load initial data programmatically

---

**Remember**: This is a learning journey. Mistakes and debugging are essential parts of the process. Every challenge is an opportunity to strengthen problem-solving skills and build confidence as an independent developer.`