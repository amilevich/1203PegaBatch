package com.example.sortemployee;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		Employee e1 = new Employee("Jevin", "Science", 23);
		Employee e2 = new Employee("Francis", "Art", 55);

		ArrayList<Employee> employee = new ArrayList<>();

		employee.add(e1);
		employee.add(e2);

		System.out.println("Unsorted: ");
		for (Employee e : employee) {
			System.out.println(e);
		}
		// Sorting by Name
		Collections.sort(employee, new SortByName());
		System.out.println("Sorted By Name: ");
		for (Employee e : employee) {
			System.out.println(e);
		}
		// Sorting by department
		Collections.sort(employee, new SortByDepartment());
		System.out.println("Sorted By Department: ");
		for (Employee e : employee) {
			System.out.println(e);
		}
		// Sorting by age
		Collections.sort(employee, new SortByAge());
		System.out.println("Sorted By Age: ");
		for (Employee e : employee) {
			System.out.println(e);
		}
	}
}
