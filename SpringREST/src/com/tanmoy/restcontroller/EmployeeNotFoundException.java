package com.tanmoy.restcontroller;

public class EmployeeNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long empId;
	public EmployeeNotFoundException(long empId){
		this.empId=empId;
	}
	public long getEmpId() {
		return empId;
	}
	

}
