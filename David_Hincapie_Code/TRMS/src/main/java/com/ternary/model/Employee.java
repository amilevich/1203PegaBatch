package com.ternary.model;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String departmentName;
	private String email;
	private String password;
	private int reportTo;
	private int departmentHeadId;
	private String phoneNumber;
	private double refund;
	private double refundPending;

	public Employee() {
		super();
	}

	public Employee(int employeeId, int departmentId, String departmentName, String firstName, String lastName,
			String title, String email, String password, int reportTo, String phoneNumber,
			double availbleFunds) {
		super();
		this.employeeId = employeeId;
		this.departmentHeadId = departmentId;
		this.departmentName = departmentName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = title;
		this.email = email;
		this.password = password;
		this.reportTo = reportTo;
		this.phoneNumber = phoneNumber;
		this.refund = availbleFunds;
	}

	public Employee(int employeeId, int departmentId, String firstName, String lastName, String title, String email,
			String password, int reportTo, String phoneNumber, double availbleFunds) {
		super();
		this.employeeId = employeeId;
		this.departmentHeadId = departmentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = title;
		this.email = email;
		this.password = password;
		this.reportTo = reportTo;
		this.phoneNumber = phoneNumber;
		this.refund = availbleFunds;
	}

	
	
	public Employee(int employeeId, String firstName, String lastName, String jobTitle, String departmentName,
			String email, String password, int reportTo, int departmentHeadId, String phoneNumber, double refund,
			double refundPending) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.departmentName = departmentName;
		this.email = email;
		this.password = password;
		this.reportTo = reportTo;
		this.departmentHeadId = departmentHeadId;
		this.phoneNumber = phoneNumber;
		this.refund = refund;
		this.refundPending = refundPending;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public int getDepartmentHeadId() {
		return departmentHeadId;
	}

	public void setDepartmentHeadId(int departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
		this.refund = refund;
	}

	public double getRefundPending() {
		return refundPending;
	}

	public void setRefundPending(double refundPending) {
		this.refundPending = refundPending;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", jobTitle=" + jobTitle + ", departmentName=" + departmentName + ", email=" + email + ", password="
				+ password + ", reportTo=" + reportTo + ", departmentHeadId=" + departmentHeadId + ", phoneNumber="
				+ phoneNumber + ", refund=" + refund + ", refundPending=" + refundPending + "]";
	}

	
	
	
	
	
}

