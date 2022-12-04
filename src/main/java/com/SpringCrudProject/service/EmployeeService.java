package com.SpringCrudProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringCrudProject.model.Employee;
import com.SpringCrudProject.repository.EmployeeRepository;
import com.SpringCrudProject.service.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee addEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	public List<Employee> addAllEmployees(List<Employee> employees) {
		
		return employeeRepository.saveAll(employees);
	}

	public Optional<Employee> getEmployeeById(int id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		System.out.println("employee----------"+employee);
		if (employee.isPresent()) {
			return employee;
		}else {
			throw new EmployeeNotFoundException("Employee not found with employee id : "+id);
		}
	}

	public Optional<Employee> getEmployeeByName(String name) throws EmployeeNotFoundException {
		Optional<Employee> employee = Optional.of(employeeRepository.findByName(name));
		System.out.println("employee----------"+employee);
		if (employee.isPresent()) {
			return employee;
		}else {
			throw new EmployeeNotFoundException("Employee not found with employee name : "+name);
		}
	}

	public Employee updateEmployee(Employee employee) {
		
		employeeRepository.save(employee);
		
		return employee;
	}

	public Optional<Employee> deleteEmployeeById(int id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		System.out.println("employee----------"+employee);
		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
			return employee;
		}else {
			throw new EmployeeNotFoundException("Employee not found with employee id : "+id);
		}
		
	}

	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}
	
}
