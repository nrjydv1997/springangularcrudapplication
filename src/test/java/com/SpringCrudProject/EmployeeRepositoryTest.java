package com.SpringCrudProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

import com.SpringCrudProject.model.Employee;
import com.SpringCrudProject.repository.EmployeeRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	// Junit test for saveEmployee
	@Test
	@Order(1)
	@Rollback(false)
	public void addEmployeeTest() {
		Employee employee = new Employee();
		employee.setName("Neeraj Yadav");
		employee.setEmail("nrjydv1997@gmail.com");
		employee.setSalary(20000);
		employeeRepository.save(employee);
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	// Test for getEmployee
	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee employee = employeeRepository.findById(4).get();
		Assertions.assertThat(employee.getId()).isEqualTo(4);
	}
	// Test for getAllEmployee
	@Test
	@Order(3)
	public void getEmployeesTest() {
		List<Employee> employees = employeeRepository.findAll();
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}
	@Test
	@Order(4)
	@Rollback(false)
	public void updateEmployeeTest() {
		Employee employee = employeeRepository.findById(4).get();
		employee.setEmail("ashishnehra1997@gmail.com");
		Employee updatedEmployee = employeeRepository.save(employee);
		Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("ashishnehra1997@gmail.com");
	}
	
	@Test
	@Order(5)
	public void deleteEmployeeTest() {
		Employee employee = employeeRepository.findById(3).get();
		employeeRepository.delete(employee);
		Employee employee1=null;
		Optional<Employee> optionalEmployee = employeeRepository.findById(3);
		if (optionalEmployee.isPresent()) {
			employee1=optionalEmployee.get();
		}

		Assertions.assertThat(employee1).isNull();
	}
}
