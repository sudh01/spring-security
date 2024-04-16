package com.example.service;

import java.util.List;

import com.example.entity.Employee;
import com.example.exception.EmployeeNotFoundException;

public interface IEmployeeService {

	List<Employee> getAllEmployees();
	Employee addEmployee(Employee emp);
	Employee getEmployeeById(int id) throws EmployeeNotFoundException;
	Employee updateEmployee(Employee emp);
	void deleteEmployeeById(int id);
	Employee updateContactNo(int id, String newContactNo);
	
}
