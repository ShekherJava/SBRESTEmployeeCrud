package com.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EMP")
@Data
public class Employee {
	@Id
	private Integer empno;
	private String ename;
	private Double sal;
	private Integer deptno;

}
