package com.tanmoy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanmoy.dao.EmployeeDAO;
import com.tanmoy.employee.dto.Employee;
import com.tanmoy.employee.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired(required=true)
	EmployeeDAO dao;
	
	@Override
	public List<Employee> getEmployees() {
		return dao.getAllEmployee();
	}

	@Override
	public Employee getEmployeeById(long empid) {
		// TODO Auto-generated method stub
		return null;
	}

}
