package com.tanmoy.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tanmoy.employee.dto.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Employee e=new Employee();
		e.setLoginID((rs.getString("LoginID")));
		e.setBusinessEntityID((rs.getInt("BusinessEntityID")));
		e.setEmployeeName(rs.getString("FirstName")+" "+rs.getString("MiddleName")!=null?rs.getString("MiddleName"):""+rs.getString("LastName"));
		e.setNationalIDNumber((rs.getString("NationalIDNumber")));
		e.setJobTitle((rs.getString("JobTitle")));
		e.setBirthDate((rs.getString("BirthDate")));
		e.setMaritalStatus((rs.getString("MaritalStatus").charAt(0)));
		e.setGender((rs.getString("Gender").charAt(0)));
		e.setHireDate((rs.getString("HireDate")));
		
		return e;

	}

}
