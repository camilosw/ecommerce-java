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

## Acceptance Criteria

* User registration and login work correctly
* JWT tokens are properly generated and validated
* Role-based access control functions as specified
* Security configurations follow best practices
