package com.SpringCrudProject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringCrudProject.model.Employee;
import com.SpringCrudProject.service.EmployeeService;
import com.SpringCrudProject.service.exception.EmployeeNotFoundException;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//Adding new employee
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
		return new ResponseEntity<>(employeeService.addEmployee(employee),HttpStatus.CREATED);
	}
	
	//Add more than one employee
	@PostMapping("/addEmployees")
	public ResponseEntity<List<Employee>> addAllEmployees(@RequestBody List<Employee> employees){
		return new ResponseEntity<>(employeeService.addAllEmployees(employees),HttpStatus.CREATED);
	}

	//get all employees
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees() {
			
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
	}
	
	//get employee by it's id
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Optional<Employee>>  getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
		return new ResponseEntity<Optional<Employee>>(employeeService.getEmployeeById(id),HttpStatus.OK);
	}
	
	//Get employee by name
	@GetMapping("/getEmployeeByName/{name}")
	public ResponseEntity<Optional<Employee>> getEmployeeByName(@PathVariable String name) throws EmployeeNotFoundException {
		return new ResponseEntity<Optional<Employee>>(employeeService.getEmployeeByName(name),HttpStatus.OK);
	}
	
	//Update Employee
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.updateEmployee(employee),HttpStatus.CREATED);
	}
	
	//delete a employee
	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
		employeeService.deleteEmployeeById(id);
		 return new ResponseEntity<String>(HttpStatus.OK);
	}
}
