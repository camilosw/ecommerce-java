# Phase 5: Advanced Testing & Quality Engineering

**Duration:** 2-3 weeks

## Context

By now you have real testing experience across three phases:
- Phase 2: repository tests with `@DataJpaTest`
- Phase 3: service/controller unit tests with Mockito and `@WebMvcTest`
- Phase 4: security tests with mock authentication

This phase is not an introduction to testing — it's about maturing your approach. You'll learn professional-grade tools and methodologies, and revisit your existing tests to apply new thinking.

## Learning Objectives

* Understand and apply the testing pyramid as a design principle
* Practice test-driven development (TDD) as a workflow
* Replace H2 with real databases in tests using Testcontainers
* Write end-to-end API tests that cover full user journeys
* Measure and improve test coverage with JaCoCo
* Automate test execution in a CI/CD pipeline

## Implementation Tasks

1. **Test-Driven Development:**

    * Learn the Red-Green-Refactor cycle: write a failing test first, make it pass, then clean up
    * Apply TDD to implement a new feature (e.g., product search, filtering, or a review entity)
    * Reflect: compare your TDD experience to the "test after" approach used in earlier phases

2. **Testcontainers (Real Database in Tests):**

    * Replace H2 in integration tests with a real PostgreSQL container using Testcontainers
    * Understand why H2 compatibility mode can mask real database bugs
    * Write integration tests that run the full application stack against a real database

3. **End-to-End API Tests:**

    * Use `TestRestTemplate` or `MockMvc` at the full-stack level to test complete user journeys
    * Examples: "a customer browses products by category", "an admin creates a product and it appears in listings"
    * Cover unhappy paths: invalid input, missing auth, non-existent resources

4. **Test Data Management:**

    * Create test data builders and factories to reduce repetitive setup code
    * Implement proper database cleanup strategies between tests
    * Use `@DirtiesContext` and `@Transactional` rollback appropriately — and understand the trade-offs of each

5. **Coverage & Static Analysis:**

    * Configure JaCoCo for code coverage reporting
    * Interpret coverage reports: understand what high coverage does and does not guarantee
    * Set up SonarQube (or SonarCloud) for static analysis
    * Review your existing codebase against the findings

6. **CI/CD Pipeline:**

    * Configure GitHub Actions (or equivalent) to run all tests on every push
    * Fail the pipeline if coverage drops below 80%
    * Understand the difference between fast unit tests and slower integration tests in pipeline design

## Acceptance Criteria

* Test coverage exceeds 80% for service and controller layers (measured by JaCoCo)
* At least one Testcontainers-based integration test runs against a real PostgreSQL instance
* End-to-end tests cover at least two complete user journeys
* All tests run independently and consistently — no order dependencies
* Test execution is automated via CI on every push
* You can articulate the trade-offs between the different test types in the testing pyramid

## Extension Challenges

* Implement mutation testing with PIT to evaluate test quality (not just coverage)
* Add performance/load testing with Gatling or k6
* Practice TDD from scratch by adding a new entity (e.g., Orders or Reviews) test-first
