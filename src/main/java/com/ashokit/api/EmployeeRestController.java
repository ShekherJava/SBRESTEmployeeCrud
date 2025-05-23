package com.ashokit.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.Employee;
import com.ashokit.exception.EmployeeAlreadyExistException;
import com.ashokit.exception.EmployeeNotFoundException;
import com.ashokit.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Employees", description = "Employee REST API")
@RestController
public class EmployeeRestController {
	
	@Autowired
	EmployeeService service;
	
	@Operation(summary = "Get employee by id", description = "Fetches employee by empno")
	@ApiResponse(responseCode = "200", description = "Employee retrieved from DB successfully")
	@ApiResponse(responseCode = "404", description = "Employee Not Found")
	@GetMapping(value = "/employee/{id}", 
			    produces = { "application/json" }
			   )
	public ResponseEntity<Employee> getEmployeeById(@Parameter(description = "empno to fetch the details") @PathVariable Integer id)
	{
		// call service.fetchEmployeeById(id)
		Employee emp = service.fetchEmployeeById(id);
		if ( emp != null)
			return  new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			throw new EmployeeNotFoundException("Employee with id : "+ id +" doesn't exist!");
	}
	
	@GetMapping(value = "/employees", produces = "application/json")
	public ResponseEntity<List<Employee>>  getEmployees() {
		
		// call service.fetchEmployees()
		List<Employee> employees = service.fetchEmployees();
		if (employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Add a new Employee", description = "Adds a new Employee to the DB, by accepting the employee in request body")
	@ApiResponse(responseCode = "201", description = "Employee is added to the Database Successfully")
	@ApiResponse(responseCode = "400", description = "Bad request")
	@PostMapping(value = "/add", 
			     consumes = { "application/json" },
			     produces = { "application/json" }
			    )
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
		
		Employee emp = service.saveEmployee(e);
		if(emp == null)
		{
			throw new EmployeeAlreadyExistException("Employee with id : " +e.getEmpno() +" already exist!");
		}
		else
		{
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping( value = "/update",
			     consumes = "application/json",
			     produces = "application/json"
			   )
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e)
	{
		Employee  updatedEmp = service.updateEmployee(e);
		if ( updatedEmp == null ) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable Integer id) {
		boolean flag = service.removeEmployee(id);
		if (flag == true)
			return new ResponseEntity<String>("Employee is deleted....", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee doesn't exist", HttpStatus.NOT_FOUND);
	}
	
	@PatchMapping(value = "/update/{id}",
			      consumes = "application/json",
			      produces = "application/json"
			     )
	public ResponseEntity<Employee> partialUpdate(@RequestBody Map<String, Object> fieldsMap, @PathVariable Integer id) {
		
		Employee updatedEmployee = service.updateEmployeeById(id, fieldsMap);
		
		if (updatedEmployee == null)
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
		
	}
	
	
}



















