package com.tanmoy.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.tanmoy.dao.EmployeeDAO;
import com.tanmoy.employee.dto.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired(required = true)
	private JdbcOperations jdbcOperation;

	private static final String GET_ALL_EMPLOYEES = "select e.LoginID,e.BusinessEntityID,p.FirstName,p.MiddleName,p.LastName,e.NationalIDNumber,"
			+ "e.JobTitle,e.BirthDate,e.MaritalStatus,e.Gender,e.HireDate from HumanResources.Employee e,Person.Person p "
			+ "where e.BusinessEntityID=p.BusinessEntityID";
	private static final String GET_AN_EMPLOYEE_BY_USERID = "insert into user_roles(username,role) values(?,?)";

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList;
		empList = jdbcOperation.query(GET_ALL_EMPLOYEES, new EmployeeMapper());
		return empList;
	}

	@Override
	public Employee getEmployeeById(long empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
