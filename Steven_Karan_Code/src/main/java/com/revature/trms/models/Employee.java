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
	private double available_funds;
	// Department information:
	private String department;

	// Position information:
	private String position;

	// Supervisor information:
	private int supervisor_id;
	private String supervisor_firstname;
	private String supervisor_lastname;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int emp_id, String firstname, String lastname, String email, String username, String password,
			double available_funds, String department, String position, int supervisor_id, String supervisor_firstname,
			String supervisor_lastname) {
		super();
		this.emp_id = emp_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.available_funds = available_funds;
		this.department = department;
		this.position = position;
		this.supervisor_id = supervisor_id;
		this.supervisor_firstname = supervisor_firstname;
		this.supervisor_lastname = supervisor_lastname;
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", available_funds=" + available_funds
				+ ", department=" + department + ", position=" + position + ", supervisor_id=" + supervisor_id
				+ ", supervisor_firstname=" + supervisor_firstname + ", supervisor_lastname=" + supervisor_lastname
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(available_funds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + emp_id;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((supervisor_firstname == null) ? 0 : supervisor_firstname.hashCode());
		result = prime * result + supervisor_id;
		result = prime * result + ((supervisor_lastname == null) ? 0 : supervisor_lastname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (Double.doubleToLongBits(available_funds) != Double.doubleToLongBits(other.available_funds))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (supervisor_firstname == null) {
			if (other.supervisor_firstname != null)
				return false;
		} else if (!supervisor_firstname.equals(other.supervisor_firstname))
			return false;
		if (supervisor_id != other.supervisor_id)
			return false;
		if (supervisor_lastname == null) {
			if (other.supervisor_lastname != null)
				return false;
		} else if (!supervisor_lastname.equals(other.supervisor_lastname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
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
	public double getAvailable_funds() {
		return available_funds;
	}
	public void setAvailable_funds(double available_funds) {
		this.available_funds = available_funds;
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

	
}
