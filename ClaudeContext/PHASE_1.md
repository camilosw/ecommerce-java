# Phase 1: Spring Boot Foundations & REST API Basics

**Duration:** 1-2 weeks

## Learning Objectives

* Master Spring Boot project setup and auto-configuration
* Implement RESTful web services with proper HTTP methods
* Practice dependency injection and component lifecycle
* Understand JSON serialization/deserialization

## Product Requirements

**Product Entity Specifications:**

* Product must have: ID (Long), name (String, max 100 chars), description (String, max 500 chars), price (BigDecimal, positive), category (String), stock quantity (Integer, non-negative)
* Products must be uniquely identifiable by ID
* Product names must be unique within the system
* Price must support decimal values with 2 decimal places
* Stock quantity cannot be negative

**Required API Endpoints:**

* `GET /api/products` - Return all products with 200 status
* `GET /api/products/{id}` - Return specific product (200 if found, 404 if not found)
* `POST /api/products` - Create new product (201 on success, 400 on validation error)
* `PUT /api/products/{id}` - Update existing product (200 on success, 404 if not found, 400 on validation error)
* `DELETE /api/products/{id}` - Remove product (204 on success, 404 if not found)

**Response Format Requirements:**

* All responses must be in JSON format
* Error responses must include timestamp, error message, and HTTP status
* Successful POST requests must return the created product with assigned ID
* All endpoints must return appropriate HTTP status codes

**Business Rules:**

* Cannot create products with duplicate names
* Cannot update product to have a name that already exists (unless it's the same product)
* Stock quantity must never go below zero
* Price must be greater than zero

**Technical Implementation Goals:** Now figure out how to implement these requirements using Spring Boot concepts.

## Acceptance Criteria

* All endpoints respond correctly with appropriate HTTP status codes
* JSON request/response bodies are properly formatted
* Service layer separation is maintained
* Basic input validation is present

## Common Pitfalls to Avoid

* Mixing business logic in controllers
* Forgetting @RestController vs @Controller annotation
* Improper HTTP method usage (using GET for data modification)
* Missing @RequestBody or @PathVariable annotations

## Extension Challenges

* Add pagination to product listing endpoint
* Implement search functionality by product name
* Add product category filtering
* Create custom exception handling for invalid product IDs
