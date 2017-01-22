package com.tanmoy.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tanmoy.dao.EmployeeDAO;
import com.tanmoy.employee.dto.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired(required = true)
	private NamedParameterJdbcOperations jdbcOperation;

	private static final String GET_ALL_EMPLOYEES = "select e.LoginID,e.BusinessEntityID,p.FirstName,p.MiddleName,p.LastName,e.NationalIDNumber,"
			+ "e.JobTitle,e.BirthDate,e.MaritalStatus,e.Gender,e.HireDate from HumanResources.Employee e,Person.Person p "
			+ "where e.BusinessEntityID=p.BusinessEntityID";

	private static final String GET_AN_EMPLOYEE_BY_USERID = "select e.LoginID as loginID,e.BusinessEntityID as businessEntityID,"
			+ "concat(p.FirstName,' ',p.MiddleName,' ',p.LastName) as employeeName,e.NationalIDNumber as nationalIDNumber,e.JobTitle as jobTitle,"
			+ "e.BirthDate as birthDate,e.MaritalStatus as maritalStatus ,e.Gender as gender	,e.HireDate as hireDate from HumanResources.Employee e,"
			+ "Person.Person p where e.BusinessEntityID=p.BusinessEntityID and e.NationalIDNumber=:loginid";

	private RowMapper<Employee> employeeMapper = new BeanPropertyRowMapper<Employee>(Employee.class);

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList;
		empList = jdbcOperation.query(GET_ALL_EMPLOYEES, new EmployeeMapper());
		return empList;
	}

	@Override
	public Employee getEmployeeById(long empId) {
		Employee empl;
		/*
		 * Map<String,Object> map=new HashMap<String,Object>();
		 * map.put("loginid",empId);
		 */ /* you can use map instead of SqlParameterSource */
		SqlParameterSource namedParameters = new MapSqlParameterSource("loginid", empId);
		try {
			empl = jdbcOperation.queryForObject(GET_AN_EMPLOYEE_BY_USERID, namedParameters, employeeMapper);
			return empl;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}