# Phase 3: Advanced Spring Features & API Refinement

**Duration:** 2-3 weeks

## Learning Objectives

* Master Bean Validation for input sanitization
* Implement global exception handling strategies
* Practice Spring configuration and property management
* Understand aspect-oriented programming concepts

## Implementation Tasks

1. **Input Validation:**

    * Add validation annotations to model classes (@NotNull, @Size, @Min, @Max)
    * Create custom validators for business rules (e.g., unique product names)
    * Implement DTO pattern for request/response separation

2. **Exception Handling:**

    * Create custom exception classes (ProductNotFoundException, InvalidInputException)
    * Implement @ControllerAdvice for global exception handling
    * Design consistent error response format with proper HTTP status codes

3. **Configuration Management:**

    * Externalize configuration using @ConfigurationProperties
    * Create profiles for different environments (dev, test, prod)
    * Implement feature toggles using Spring Boot properties

4. **API Documentation:**

    * Integrate SpringDoc OpenAPI for automatic documentation
    * Add detailed API descriptions and examples
    * Configure Swagger UI for interactive testing

## Acceptance Criteria

* All API inputs are properly validated with meaningful error messages
* Global exception handling provides consistent error responses
* API documentation is automatically generated and accessible
* Different environment profiles work correctly

## Unit Testing with Mocks (Introduced This Phase)

Phase 2 tested the database layer in isolation. This phase introduces testing the **service and controller layers** in isolation — without a database or a running server. The key tool is Mockito.

**What you'll learn:**

* Mockito basics: `@Mock`, `@InjectMocks`, `when(...).thenReturn(...)`, `verify(...)`
* Why mocking exists: test one layer at a time by replacing real dependencies with controlled fakes
* Service layer unit tests with `@ExtendWith(MockitoExtension.class)`: mock the repository, test the business logic
* Controller tests with `@WebMvcTest`: starts only the web layer, mock the service, test request/response behavior (status codes, JSON structure, validation rejection)
* Testing exception paths: verify that your `@ControllerAdvice` returns the right error response when exceptions are thrown

**Acceptance Criteria — Testing:**

* `ProductServiceTest` exists and covers: create, update, delete, find, and error cases (e.g., product not found)
* `ProductControllerTest` exists using `@WebMvcTest` and covers all endpoints including validation rejection (400) and not-found (404) cases
* Mockito is used — no real database or server involved in these tests

## Extension Challenges

* Implement API versioning strategies
* Add request/response logging using AOP
* Create health check endpoints using Spring Actuator
* Implement API rate limiting
