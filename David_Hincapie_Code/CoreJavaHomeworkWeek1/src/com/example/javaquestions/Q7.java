package com.example.javaquestions;
import java.util.ArrayList;
import java.util.Collections;

public class Q7 {
	// Q7. Sort two employees based on their name, department, and age using the
	// Comparator interface.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee jacks = new Employee("jacks", "wood department", 28);
		Employee jill = new Employee("jill", "herbs department", 34);

		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(jacks);
		employees.add(jill);

		Collections.sort(employees, new SortByName());
		System.out.println("\nSortByName");
		for (int i = 0; i < employees.size(); i++)
			System.out.println(employees.get(i).getName() + "  " + employees.get(i));

		Collections.sort(employees, new SortByDepartment());
		System.out.println("\nSortByDepartment");
		for (int i = 0; i < employees.size(); i++)
			System.out.println(employees.get(i).getDepartment() + "  " + employees.get(i));

		Collections.sort(employees, new SortByAge());
		System.out.println("\nSortByAge");
		for (int i = 0; i < employees.size(); i++)
			System.out.println(employees.get(i).getAge() + "  " + employees.get(i));
		
		
	}

}
