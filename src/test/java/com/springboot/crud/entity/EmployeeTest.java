package com.springboot.crud.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class EmployeeTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Initialize the validator for Bean Validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEmployeeConstructor() {
        // Create a new Employee instance
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", false, LocalDateTime.now(), LocalDateTime.now());

        // Assert that the employee fields are set correctly
        assertEquals(1, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertFalse(employee.isDeleted());
    }

    @Test
    void testValidEmployee() {
        // Create a valid Employee instance
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", false, LocalDateTime.now(), LocalDateTime.now());

        // Validate the employee object
        var violations = validator.validate(employee);

        // Assert that no violations are found
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmail() {
        // Create an invalid Employee instance (invalid email)
        Employee employee = new Employee(1, "John", "Doe", "john.doe@com", false, LocalDateTime.now(), LocalDateTime.now());

        // Validate the employee object
        var violations = validator.validate(employee);

        // Assert that there is one violation (invalid email)
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void testFirstNameValidation() {
        // Create an Employee instance with an empty first name
        Employee employee = new Employee(1, "", "Doe", "john.doe@example.com", false, LocalDateTime.now(), LocalDateTime.now());

        // Validate the employee object
        var violations = validator.validate(employee);

        // Assert that there is a violation on the first name field
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("firstName")));
    }

    @Test
    void testLastNameTooLong() {
        // Create an Employee instance with a last name exceeding 50 characters
        Employee employee = new Employee(1, "John", "DoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoeDoe", "john.doe@example.com", false, LocalDateTime.now(), LocalDateTime.now());

        // Validate the employee object
        var violations = validator.validate(employee);

        // Assert that there is a violation on the last name field
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("lastName")));
    }

    @Test
    void testCreatedAtAndUpdatedAt() {
        // Create an Employee instance
        LocalDateTime now = LocalDateTime.now();
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com", false, now, now);

        // Assert that creation and update timestamps are set
        assertNotNull(employee.getCreatedAt());
        assertNotNull(employee.getUpdatedAt());
        assertEquals(now.getYear(), employee.getCreatedAt().getYear());
    }
}
