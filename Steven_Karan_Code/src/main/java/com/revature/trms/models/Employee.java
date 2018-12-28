package com.revature.trms.models;

/*
 * Employee model which reflects the custom view which puts relevant values alongside the corresponding ids
 * in the employee table in order to increase readability and reduce database calls
 */
public class Employee {
	// Employee information:
	private int emp_id;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	
	// Department information:
	private int department_id;
	private String department_name;
	
	// Title information:
	private int title_id;
	private String title_name;
	
	// Supervisor information:
	private int supervisor_id;
	private String supervisor_firstname;
	private String supervisor_lastname;
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getTitle_id() {
		return title_id;
	}
	public void setTitle_id(int title_id) {
		this.title_id = title_id;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	public int getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}
	public String getSupervisor_firstname() {
		return supervisor_firstname;
	}
	public void setSupervisor_firstname(String supervisor_firstname) {
		this.supervisor_firstname = supervisor_firstname;
	}
	public String getSupervisor_lastname() {
		return supervisor_lastname;
	}
	public void setSupervisor_lastname(String supervisor_lastname) {
		this.supervisor_lastname = supervisor_lastname;
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", department_id=" + department_id
				+ ", department_name=" + department_name + ", title_id=" + title_id + ", title_name=" + title_name
				+ ", supervisor_id=" + supervisor_id + ", supervisor_firstname=" + supervisor_firstname
				+ ", supervisor_lastname=" + supervisor_lastname + "]";
	}
	public Employee(int emp_id, String firstname, String lastname, String email, String username, String password,
			int department_id, String department_name, int title_id, String title_name, int supervisor_id,
			String supervisor_firstname, String supervisor_lastname) {
		super();
		this.emp_id = emp_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.department_id = department_id;
		this.department_name = department_name;
		this.title_id = title_id;
		this.title_name = title_name;
		this.supervisor_id = supervisor_id;
		this.supervisor_firstname = supervisor_firstname;
		this.supervisor_lastname = supervisor_lastname;
	}
	
	public Employee(int emp_id, String firstname, String lastname, String email, String username, String password,
			int department_id, int title_id, int supervisor_id) {
		super();
		this.emp_id = emp_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.department_id = department_id;
		this.title_id = title_id;
		this.supervisor_id = supervisor_id;

	}
	public Employee() {
		super();
	}
	
}
