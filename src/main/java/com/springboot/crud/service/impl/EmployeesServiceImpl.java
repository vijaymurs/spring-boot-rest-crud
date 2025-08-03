package com.springboot.crud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.exception.ResourceNotFoundException;
import com.springboot.crud.repository.EmployeesRepository;
import com.springboot.crud.service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeesRepository.findByIsDeletedFalse();
    }

    @Override
    public Employee getEmployee(int employeeId) {
        // Using Optional.orElseThrow to directly throw exception if employee is not found
        return employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id - " + employeeId));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        if (!employeesRepository.existsById(employee.getId())) {
            throw new ResourceNotFoundException("Employee not found with id - " + employee.getId());
        }
        return employeesRepository.save(employee);
    }

    @Override
    @Transactional
    public String deleteEmployee(int employeeId) {
        Employee employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id - " + employeeId));
        
        employee.setDeleted(true);
        // Save is required to persist the deleted status
        employeesRepository.save(employee);
        return "Employee with id " + employeeId + " marked as deleted.";
    }
}
