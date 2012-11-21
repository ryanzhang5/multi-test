package org.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDaoImpl implements EmployeeDao {
	private JdbcTemplate jdbcTemplate;

	public int countAll() {
		int rowCount = this.jdbcTemplate
				.queryForInt("select count(*) from employees");
		return rowCount;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int countAllWithPapameter() {
		int rows = this.jdbcTemplate.queryForInt(
				"select count(*) from employees where firstname = ?",
				new Object[] { "aaa" });
		return rows;
	}

	public String getNameById() {
		String firstname = (String) this.jdbcTemplate.queryForObject(
				"select firstname from employees where id = ?",
				new Object[] { new Long(1) }, String.class);
		return firstname;
	}

	@SuppressWarnings("unchecked")
	public Employee getEmployeeById() {
		Employee employee = (Employee) this.jdbcTemplate.queryForObject(
				"select firstname,lastname from employees where id = ?",
				new Object[] { new Long(1) }, new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee emp = new Employee();
						emp.setFirstname(rs.getString("firstname"));
						emp.setLastname(rs.getString("lastname"));
						return emp;
					}
				});
		return employee;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		List<Employee> employees = this.jdbcTemplate.query(
				"select firstname,lastname from employees ",
				new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee emp = new Employee();
						emp.setFirstname(rs.getString("firstname"));
						emp.setLastname(rs.getString("lastname"));
						return emp;
					}
				});
		return employees;
	}

	public void addEmployee() {
		this.jdbcTemplate.update(
				"insert into employees (firstname, lastname) values (?, ?)",
				new Object[] { "Leonor", "Watling" });
	}

	public void deleteEmployee() {
		this.jdbcTemplate.update("update employees set firstname = ? where id = ?",
				new Object[] { "Banjo", new Long(3) });
	}

	public void updateEmployee() {
		this.jdbcTemplate.update("delete from employees where id = ?",
				new Object[] {new Long(2) });
	}

}