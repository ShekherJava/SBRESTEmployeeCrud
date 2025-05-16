package com.ashokit.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMP")
@Data
@Schema(description = "Employee model")
public class Employee {
	
	@Schema(description = "id of the employee")
	@Id
	private Integer empno;
	
	@Schema(description  ="name of the employee")
	private String ename;
	
	@Schema(description = "salary of the employee")
	private Double sal;
	
	@Schema(description = "deptno of the employee")
	private Integer deptno;

}
