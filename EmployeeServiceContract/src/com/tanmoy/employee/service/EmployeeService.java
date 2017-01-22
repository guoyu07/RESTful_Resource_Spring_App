package com.tanmoy.employee.service;

import java.util.List;

import com.tanmoy.employee.dto.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees();
	public Employee getEmployeeById(long empid);
}
