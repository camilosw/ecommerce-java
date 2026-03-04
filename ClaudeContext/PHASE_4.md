# Phase 4: Security & Authentication

**Duration:** 2-3 weeks

## Learning Objectives

* Implement JWT-based authentication and authorization
* Master Spring Security configuration
* Practice role-based access control
* Understand security best practices

## Implementation Tasks

1. **User Management:**

    * Create User entity with roles (ADMIN, CUSTOMER)
    * Implement user registration and login endpoints
    * Add password encryption using BCrypt

2. **JWT Implementation:**

    * Configure JWT token generation and validation
    * Create authentication filter for token processing
    * Implement refresh token mechanism

3. **Authorization Rules:**

    * Secure product management endpoints (admin-only for CREATE, UPDATE, DELETE)
    * Allow public access to product viewing endpoints
    * Implement user-specific data access patterns

4. **Security Hardening:**

    * Configure CORS policies
    * Implement CSRF protection where appropriate
    * Add security headers and HTTPS configuration

## Security Testing (Introduced This Phase)

Security is hard to verify manually — you need tests that prove your access rules are enforced and catch regressions when security config changes.

**What you'll learn:**

* `@WithMockUser(roles = "ADMIN")` and `@WithMockUser(roles = "CUSTOMER")` — simulate authenticated users in `@WebMvcTest` tests
* Testing unauthorized access: verify that a 401 or 403 is returned when no token or wrong role is used
* Testing authorized access: verify that an admin can create/update/delete, a customer cannot
* Integration test patterns for auth flows: test registration → login → use token end-to-end

**Acceptance Criteria — Testing:**

* Security tests cover all three access levels: unauthenticated (401), wrong role (403), correct role (200/201/204)
* At least one end-to-end test covers the full login flow and uses the returned token on a subsequent request

## Acceptance Criteria

* User registration and login work correctly
* JWT tokens are properly generated and validated
* Role-based access control functions as specified
* Security configurations follow best practices
