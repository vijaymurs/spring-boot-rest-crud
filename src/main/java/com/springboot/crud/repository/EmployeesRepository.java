package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.entity.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

}
