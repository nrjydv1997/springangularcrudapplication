package com.SpringCrudProject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.SpringCrudProject.model.Employee;
import com.SpringCrudProject.repository.EmployeeRepository;

@SpringBootTest

class SpringAngularCrudApplication1ApplicationTests {

	
	@Test
	void contextLoads() {
		System.out.println("testing is running...............");
	}
	
}
