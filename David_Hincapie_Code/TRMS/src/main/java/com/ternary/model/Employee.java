package com.ternary.model;

public class Employee {

	private int employeeId;
	private int departmentId;
	private String departmentName;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String email;
	private String password;
	private int reportTo;
	private String phoneNumber;
	private double availbleFunds;

	public Employee() {
		super();
	}

	public Employee(int employeeId, int departmentId, String departmentName, String firstName, String lastName,
			String title, String email, String password, int reportTo, String phoneNumber,
			double availbleFunds) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = title;
		this.email = email;
		this.password = password;
		this.reportTo = reportTo;
		this.phoneNumber = phoneNumber;
		this.availbleFunds = availbleFunds;
	}

	public Employee(int employeeId, int departmentId, String firstName, String lastName, String title, String email,
			String password, int reportTo, String phoneNumber, double availbleFunds) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = title;
		this.email = email;
		this.password = password;
		this.reportTo = reportTo;
		this.phoneNumber = phoneNumber;
		this.availbleFunds = availbleFunds;
	}

	public Employee(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReportTo() {
		return reportTo;
	}

	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getAvailbleFunds() {
		return availbleFunds;
	}

	public void setAvailbleFunds(double availbleFunds) {
		this.availbleFunds = availbleFunds;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", firstName=" + firstName + ", lastName=" + lastName + ", jobTitle=" + jobTitle
				+ ", email=" + email + ", password=" + password + ", reportTo=" + reportTo + ", phoneNumber="
				+ phoneNumber + ", availbleFunds=" + availbleFunds + "]";
	}



}
