package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.service.EmployeesService;

@RestController
@RequestMapping("api/emp")
public class EmployeesController {
	
	private EmployeesService employeesService;
	
	@Autowired
	public void setEmployeesService(EmployeesService theEmployeesService) {
		employeesService = theEmployeesService; 
	}

	@GetMapping("/employees")
	public List<Employee> findAllEmployee() {
		return employeesService.findAllEmployee();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		return employeesService.getEmployee(employeeId);
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeesService.addEmployee(employee);
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeesService.updateEmployee(employee);
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		return employeesService.deleteEmployee(employeeId);
	}

}
