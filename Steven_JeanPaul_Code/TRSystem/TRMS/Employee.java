/*
Employee
BenCoEmployee

EmployeeDAO
EmployeeDAOImpl
*/

public class Employee {
	private int employeeId;
	private String jobLevel = "Employee";
	private int supervisorId;
	private String department;
	private String firstName;
	private String lastName;

	

	public int getEmployeeID() {
		return employeeId;
	}

	public void setEmployeeID(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getJoblevel() {
		return jobLevel;
	}

	public void setJoblevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public int SupervisorID() {
		return supervisorId;
	}

	public void SupervisorID(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	
}