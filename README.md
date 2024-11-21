
# Spring Boot REST CRUD Application

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8.6-blue.svg)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue.svg)](https://www.postgresql.org/)



This is a simple Spring Boot application that demonstrates how to build a RESTful API for performing CRUD (Create, Read, Update, Delete) operations using Spring Boot, JPA, and PostgreSQL.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)

## Features

- RESTful API with CRUD operations for managing employee data.
- JPA for interacting with a PostgreSQL database.
- Spring Boot for creating the application with minimal configuration.
- Input validation using `@NotBlank`, `@Size`, and `@Email` annotations.
- Spring Data JPA for managing database operations.

## Technologies Used

- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok** (for reducing boilerplate code)
- **Spring Validation** (for input validation)
- **JUnit** (for testing)
- **Spring Boot DevTools** (for hot reloading during development)

## Prerequisites

Before you begin, make sure you have the following installed on your local machine:

- **Java 17 or later** (for Spring Boot 3.x compatibility)
- **Maven** (for building and running the application)
- **PostgreSQL** (for the database)
  - A PostgreSQL instance running locally or on a remote server.

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/spring-boot-rest-crud.git
cd spring-boot-rest-crud
```

### 2. Configure PostgreSQL Database

- Create a PostgreSQL database (e.g., `spring_boot`).
- Update the `application.yml` file with your PostgreSQL credentials.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/spring_boot
    username: postgres
    password: your-password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update  # Options: create, update, create-drop, validate
    show-sql: true
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

### 3. Build the Project

Using Maven, you can build the project by running the following command:

```bash
mvn clean install
```

## Running the Application

After the project is built, you can run the Spring Boot application using the following command:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

Here are the available API endpoints for managing employee data:

### 1. **Create an Employee**

- **POST** `/api/v1/employees`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "isDeleted": false,
    "createdAt": "2024-11-21T12:00:00",
    "updatedAt": "2024-11-21T12:00:00"
  }
  ```

### 2. **Get All Employees**

- **GET** `/api/v1/employees`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "isDeleted": false,
      "createdAt": "2024-11-21T12:00:00",
      "updatedAt": "2024-11-21T12:00:00"
    },
    {
      "id": 2,
      "firstName": "Jane",
      "lastName": "Smith",
      "email": "jane.smith@example.com",
      "isDeleted": false,
      "createdAt": "2024-11-21T12:15:00",
      "updatedAt": "2024-11-21T12:15:00"
    }
  ]
  ```

### 3. **Get Employee by ID**

- **GET** `/api/v1/employees/{employeeId}`
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "isDeleted": false,
    "createdAt": "2024-11-21T12:00:00",
    "updatedAt": "2024-11-21T12:00:00"
  }
  ```

### 4. **Update an Employee**

- **PUT** `/api/v1/employees`
- **Request Body**:
  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@newdomain.com"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@newdomain.com",
    "isDeleted": false,
    "createdAt": "2024-11-21T12:00:00",
    "updatedAt": "2024-11-21T12:30:00"
  }
  ```

### 5. **Delete an Employee**

- **DELETE** `/api/v1/employees/{employeeId}`
- **Response**:
  ```json
  {
    "message": "Employee with ID 1 deleted successfully."
  }
  ```
