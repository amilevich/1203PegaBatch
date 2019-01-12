package com.revature.bean;

public class Sessions {

	Management manager;
	Employee employee;
	Login login;
	
	public Sessions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sessions(Management manager, Employee employee, Login login) {
		super();
		this.manager = manager;
		this.employee = employee;
		this.login = login;
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

	@Override
	public String toString() {
		return "Sessions [manager=" + manager + ", employee=" + employee + ", login=" + login + "]";
	}
}
