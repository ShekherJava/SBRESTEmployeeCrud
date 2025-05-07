package com.ashokit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.Employee;
import com.ashokit.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository repository;

	@Override
	public Employee fetchEmployeeById(Integer id) {
		
		Optional<Employee> opt = repository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}

	@Override
	public List<Employee> fetchEmployees() {
		return repository.findAll();
	}
	
	@Override
	public Employee saveEmployee(Employee emp) {
		if (repository.existsById(emp.getEmpno()))
		{
			return null;
		}
		else {
			return repository.save(emp);
		}
		
	}
	
	@Override
	public Employee updateEmployee(Employee emp) {
		if (repository.existsById(emp.getEmpno()))
		{
			return repository.save(emp);
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public boolean removeEmployee(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		else
			return false;
	}
	
	@Override
	public Employee updateEmployeeById(Integer id, Map<String,Object> fieldsMap) {
		
		if (repository.existsById(id)){
			
			double sal = (double)fieldsMap.get("sal");
			int deptno = (int)fieldsMap.get("deptno");
			repository.partialUpdateEmp(sal, deptno, id);
			
			return repository.findById(id).get();
		}
		else {
			return null;
		}
	};

}
