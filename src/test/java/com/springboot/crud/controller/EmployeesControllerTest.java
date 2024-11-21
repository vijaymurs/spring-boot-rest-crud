package com.springboot.crud.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.service.EmployeesService;

class EmployeesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeesService employeesService;

    @InjectMocks
    private EmployeesController employeesController;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
        employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John Doe");
        employee.setDeleted(false);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeesService.findAllEmployees()).thenReturn(List.of(employee));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetEmployee() throws Exception {
        when(employeesService.getEmployee(1)).thenReturn(employee);

        mockMvc.perform(get("/api/v1/employees/{employeeId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(employeesService.addEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees")
                        .contentType("application/json")
                        .content("{\"name\": \"John Doe\", \"employeeId\": 1, \"deleted\": false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        when(employeesService.updateEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/api/v1/employees")
                        .contentType("application/json")
                        .content("{\"name\": \"John Doe\", \"employeeId\": 1, \"deleted\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeesService).deleteEmployee(1);

        mockMvc.perform(delete("/api/v1/employees/{employeeId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee with ID 1 deleted successfully."));
    }
}

