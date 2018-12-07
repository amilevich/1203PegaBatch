package com.example.sortemployee;

public class Employee {
	private String Name;
	private String Department;
	private int Age;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	@Override
	public String toString() {
		return "Employee [Name=" + Name + ", Department=" + Department + ", Age=" + Age + "]";
	}

	public Employee(String name, String department, int age) {
		super();
		Name = name;
		Department = department;
		Age = age;
	}

}
