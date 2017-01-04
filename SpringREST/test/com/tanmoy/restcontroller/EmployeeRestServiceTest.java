package com.tanmoy.restcontroller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tanmoy.employee.dto.Employee;
import com.tanmoy.employee.service.EmployeeService;
import com.tanmoy.restcontroller.EmployeeRestService;

public class EmployeeRestServiceTest {

	@InjectMocks
	EmployeeRestService test; // Component under test

	@Mock
	EmployeeService employeeService; // dependency component

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		// You can use below two lines of code if you don't use @InjectMocks.
		// For that you need to create a setter method setEmployeeService in
		// the class under test, i,e. EmployeeRestService.

		// test=new EmployeeRestService();
		// test.setEmployeeService(employeeService);

	}

	@Test
	public void getAllEmployees_returns_resultFromService() {
		List<Employee> expected = new ArrayList<Employee>();
		when(employeeService.getEmployees()).thenReturn(expected);

		List<Employee> result = test.getAllEmployees();

		assertEquals(expected, result);

	}

}
