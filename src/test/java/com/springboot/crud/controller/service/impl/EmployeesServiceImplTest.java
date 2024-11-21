package com.springboot.crud.controller.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.repository.EmployeesRepository;
import com.springboot.crud.service.impl.EmployeesServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceImplTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeesServiceImpl employeesService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John Doe");
        employee.setDeleted(false);
    }

    @Test
    public void testFindAllEmployees() {
        when(employeesRepository.findAll()).thenReturn(List.of(employee));

        var employees = employeesService.findAllEmployees();

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getFirstName());
    }

    @Test
    public void testGetEmployee_Success() {
        when(employeesRepository.findById(1)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeesService.getEmployee(1);

        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getFirstName());
    }

    @Test
    public void testGetEmployee_NotFound() {
        when(employeesRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeesService.getEmployee(1));
        assertEquals("Employee not found with id - 1", exception.getMessage());
    }

    @Test
    public void testAddEmployee() {
        when(employeesRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeesService.addEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getFirstName());
        verify(employeesRepository, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee_Success() {
        when(employeesRepository.existsById(1)).thenReturn(true);
        when(employeesRepository.save(employee)).thenReturn(employee);

        Employee updatedEmployee = employeesService.updateEmployee(employee);

        assertNotNull(updatedEmployee);
        assertEquals("John Doe", updatedEmployee.getFirstName());
        verify(employeesRepository, times(1)).save(employee);
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        when(employeesRepository.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeesService.updateEmployee(employee));
        assertEquals("Employee not found with id - 1", exception.getMessage());
    }

    @Test
    public void testDeleteEmployee_Success() {
        when(employeesRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeesRepository.save(employee)).thenReturn(employee);

        String response = employeesService.deleteEmployee(1);

        assertEquals("Employee with id 1 marked as deleted.", response);
        assertTrue(employee.isDeleted());
        verify(employeesRepository, times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        when(employeesRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> employeesService.deleteEmployee(1));
        assertEquals("Employee not found with id - 1", exception.getMessage());
    }
}
