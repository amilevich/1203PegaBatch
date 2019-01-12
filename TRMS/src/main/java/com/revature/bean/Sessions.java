package com.revature.bean;

import java.util.List;

public class Sessions {

	Management manager;
	Employee employee;
	Login login;
	Department department;
	List<Application> apps;
	
	public Sessions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sessions(Management manager, Employee employee, Login login, Department department) {
		super();
		this.manager = manager;
		this.employee = employee;
		this.login = login;
		this.department = department;
	}

	public Management getManager() {
		return manager;
	}

	public void setManager(Management manager) {
		this.manager = manager;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Sessions [manager=" + manager + ", employee=" + employee + ", login=" + login + "]";
	}
}
