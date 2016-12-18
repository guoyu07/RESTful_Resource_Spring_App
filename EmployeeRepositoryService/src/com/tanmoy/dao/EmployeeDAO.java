package com.tanmoy.dao;

import java.util.List;

import com.tanmoy.employee.dto.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployee();
	public Employee getEmployeeById(long empId);
}
