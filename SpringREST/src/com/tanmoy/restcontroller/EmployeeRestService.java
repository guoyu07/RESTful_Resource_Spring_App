package com.tanmoy.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tanmoy.employee.dto.Employee;
import com.tanmoy.employee.service.EmployeeService;


//@RestController ---if you use this instead of @Controller then @ResponseBody will not be required
//@ResponseBody is used to apply automatic message conversion when sending the response to the client.
//Similarly @RequestBody is used to apply automatic message conversion when receiving request from the client. Then the request method must be POST and not get 
@RestController
@RequestMapping("/employee")
public class EmployeeRestService {
	
	@Autowired(required=true)
	@Qualifier("employeeService")
	EmployeeService employeeService;
	
	/*public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}*/

	@RequestMapping(method=RequestMethod.GET)
	public String ping(){
		return "OK";
	}
	
	//READ ALL
	@RequestMapping(value="/all",method=RequestMethod.GET,produces="application/json")
	public List<Employee> getAllEmployees(){
		List<Employee> empList;
		
		empList=employeeService.getEmployees();
		
		
		if(empList==null){
			throw new EmployeeNotFoundException(0);
		}
		return empList;
	}
	
	//READ
	@RequestMapping(value="/{loginId}",method=RequestMethod.GET,produces="application/json")
	public Employee getEmployee(
		@PathVariable(value="loginId")	long extrnId
			){
		Employee emp1;
		/*emp1.setNationalIDNumber(extrnId);
		emp1.setEmployeeName("Tanmoy");*/
		
		emp1=employeeService.getEmployeeById(extrnId);
		
		if(emp1==null){
			throw new EmployeeNotFoundException(extrnId);
		}
		return emp1;
	}
	
	//CREATE
	@RequestMapping(value = "/new",method=RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	//@ResponseStatus is used to set the response's status code (If you don't use this then default response status code will be 200) 
	public Employee createEmployee(@RequestBody Employee emp) {

		//customerDAO.create(customer);

		return new Employee();
	}
	
	//DELETE
	@RequestMapping(value = "/del/{loginId}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable String loginId) {

		/*if (null == customerDAO.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}*/

		return new ResponseEntity<String>(loginId, HttpStatus.OK);

	}

	
	//UPDATE
	@RequestMapping(value="/update/{loginId}",method=RequestMethod.PUT)
	public ResponseEntity<Employee> updateCustomer(@PathVariable String loginId, @RequestBody Employee emp) {

		/*customer = customerDAO.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}*/

		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error employeeNotFound(EmployeeNotFoundException e){
		long empId=e.getEmpId();
		return new Error(4,"Employee ["+empId+"] not found");
	}
	
}
