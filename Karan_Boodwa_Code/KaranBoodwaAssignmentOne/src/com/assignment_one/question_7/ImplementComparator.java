package com.assignment_one.question_7;

import java.util.Comparator;

// Q7. Sort two employees based on their name, department, and age using the 
// Comparator interface.

// Employee class created and fleshed out
// An employee is defined by their name, department, and age
class Employee {
	private String name;
	private String department;
	private int age;

	// Generated constructors & getters/setters & toString
	public Employee() {

	}

	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}

}

// SortByName compares two given Employees based on their age (lexicographically)
class SortByName implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		// Return -1, 0, or 1 based on whose name comes first lexicographically
		// The strings are compared using compareTo() which returns the difference
		// between the
		// first 2 'differing' characters. 0 if they are the same.
		int order = o1.getName().compareTo(o2.getName());
		if (order < 0) {
			return -1;
		} else if (order == 0) {
			return 0;
		} else {
			return 1;
		}
	}

}

// SortByDepartment compares two given Employees based on their department (lexicographically)
class SortByDepartment implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {

		// Return -1, 0, or 1 based on whose department comes first lexicographically
		// The strings are compared using compareTo() which returns the difference
		// between the
		// first 2 'differing' characters. 0 if they are the same.
		int order = o1.getDepartment().compareTo(o2.getDepartment());
		if (order < 0) {
			return -1;
		} else if (order == 0) {
			return 0;
		} else {
			return 1;
		}

	}

}

// SortByAge compares two given Employees based on their ages (integer comparison)
class SortByAge implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {

		// Return -1, 0, or 1 based on whose age is lower
		if (o1.getAge() < o2.getAge()) {
			return -1;
		} else if (o1.getAge() == o2.getAge()) {
			return 0;
		} else {
			return 1;
		}

	}

}

public class ImplementComparator {
	public static void main(String[] args) {

		Employee emps[] = new Employee[2];
		emps[0] = new Employee("John Doe", "Secret", 23);
		emps[1] = new Employee("Jane Doe", "Super Secret", -1);

		System.out.println("Initial order:");
		for (Employee e : emps) {
			System.out.println(e.toString());
		}

		System.out.println("Sorted by Name:");
		if (new SortByName().compare(emps[0], emps[1]) == 1) {
			Employee tEmp = emps[0];
			emps[0] = emps[1];
			emps[1] = tEmp;
		}
		for (Employee e : emps) {
			System.out.println(e.toString());
		}

		System.out.println("Sorted by Department:");
		if (new SortByDepartment().compare(emps[0], emps[1]) == 1) {
			Employee tEmp = emps[0];
			emps[0] = emps[1];
			emps[1] = tEmp;
		}
		for (Employee e : emps) {
			System.out.println(e.toString());
		}

		System.out.println("Sorted by Age:");
		if (new SortByAge().compare(emps[0], emps[1]) == 1) {
			Employee tEmp = emps[0];
			emps[0] = emps[1];
			emps[1] = tEmp;
		}
		for (Employee e : emps) {
			System.out.println(e.toString());
		}

	}

}
