package org.spring.dao;

import java.util.List;

public interface EmployeeDao {
	public int countAll();

	public int countAllWithPapameter();

	public String getNameById();

	public Employee getEmployeeById();

	public List<Employee> getAllEmployees();

	public void addEmployee();

	public void deleteEmployee();

	public void updateEmployee();

}
