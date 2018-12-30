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
	private String department;

	// Position information:
	private String position;

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
	
	

	public Employee(String firstname, String lastname, String email, String username, String password,
			String department, String position, int supervisor_id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.department = department;
		this.position = position;
		this.supervisor_id = supervisor_id;
	}

	public Employee(int emp_id, String firstname, String lastname, String email, String username, String password,
			String department, String position, int supervisor_id, String supervisor_firstname,
			String supervisor_lastname) {
		super();
		this.emp_id = emp_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.department = department;
		this.position = position;
		this.supervisor_id = supervisor_id;
		this.supervisor_firstname = supervisor_firstname;
		this.supervisor_lastname = supervisor_lastname;
	}

	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", department=" + department + ", position="
				+ position + ", supervisor_id=" + supervisor_id + ", supervisor_firstname=" + supervisor_firstname
				+ ", supervisor_lastname=" + supervisor_lastname + "]";
	}

	public Employee() {
		super();
	}

}
