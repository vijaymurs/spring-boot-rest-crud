package com.springboot.crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.crud.entity.Employee;
import com.springboot.crud.repository.EmployeesRepository;
import com.springboot.crud.service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	private EmployeesRepository employeesRepository;
	
	@Autowired
	public void setEmployeesRepository(EmployeesRepository theEmployeesRepository) {
		employeesRepository =  theEmployeesRepository;
	}

	@Override
	public List<Employee> findAllEmployee() {		
		return employeesRepository.findAll();
	}

	@Override
	public Employee getEmployee(int employeeId) {
		Optional<Employee> emp =  employeesRepository.findById(employeeId);
		return (emp.isPresent() ? emp.get() : new Employee());
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeesRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeesRepository.save(employee);
	}

	@Override
	public String deleteEmployee(int employeeId) {
		Optional<Employee> emp =  employeesRepository.findById(employeeId);
		if(emp.isPresent()) {
			Employee employee =  emp.get();
			employee.setDeleted(true);
			employeesRepository.save(employee);
			return "Deleted Employee Id is "+ employeeId;
		} else {
			return "Not found Employee Id "+ employeeId;
		}
		
		
	}

}
