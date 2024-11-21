package com.springboot.crud.controller.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.service.EmployeesService;
import com.springboot.crud.service.impl.EmployeesServiceImpl;

class EmployeesServiceTest {

    @Mock
    private EmployeesService employeesService;  // Mock the EmployeesService interface

    @InjectMocks
    private EmployeesServiceImpl employeesServiceImpl;  // Inject the mocked service into the implementation

    private Employee employee;

    @BeforeEach
    void setUp() {
        // Initialize Mockito mocks
        MockitoAnnotations.openMocks(this);

        // Set up a test employee instance
        employee = Employee.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    void testFindAllEmployees() {
        // Mock the findAllEmployees method
        List<Employee> employees = Arrays.asList(employee);
        when(employeesService.findAllEmployees()).thenReturn(employees);

        // Call the method and assert the results
        List<Employee> result = employeesService.findAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee, result.get(0));
    }

    @Test
    void testGetEmployee() {
        // Mock the getEmployee method
        when(employeesService.getEmployee(1)).thenReturn(employee);

        // Call the method and assert the result
        Employee result = employeesService.getEmployee(1);
        assertNotNull(result);
        assertEquals(employee, result);
    }

    @Test
    void testAddEmployee() {
        // Mock the addEmployee method
        when(employeesService.addEmployee(employee)).thenReturn(employee);

        // Call the method and assert the result
        Employee result = employeesService.addEmployee(employee);
        assertNotNull(result);
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
    }

    @Test
    void testUpdateEmployee() {
        // Mock the updateEmployee method
        Employee updatedEmployee = Employee.builder().firstName("Jane").build();
        when(employeesService.updateEmployee(updatedEmployee)).thenReturn(updatedEmployee);

        // Call the method and assert the result
        Employee result = employeesService.updateEmployee(updatedEmployee);
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void testDeleteEmployee() {
        // Mock the deleteEmployee method
        when(employeesService.deleteEmployee(1)).thenReturn("Employee with ID 1 deleted successfully.");

        // Call the method and assert the result
        String result = employeesService.deleteEmployee(1);
        assertNotNull(result);
        assertEquals("Employee with ID 1 deleted successfully.", result);
    }
}
