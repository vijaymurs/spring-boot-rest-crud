package com.springboot.crud.service;

import java.util.List;

import com.springboot.crud.entity.Employee;

public interface EmployeesService {

	List<Employee> findAllEmployee();
	
	Employee getEmployee(int employeeId);
	
	Employee addEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee);

	String deleteEmployee(int employeeId);
}
