package com.homework.q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Employee> employee = new ArrayList<>();
		
		employee.add(new Employee("Judai", "HR", 26));
		employee.add(new Employee("Kairi", "Finace", 24));
		
		System.out.println("Employees : " + employee);
		
		// Sort employees by Age
        Collections.sort(employee, Comparator.comparingInt(Employee::getAge));
        System.out.println("\nEmployees (Sorted by Age) : " + employee);

        //Collections.sort(employee, Comparator.comparing(Employee::getName).thenComparing(Employee::getDepartment).thenComparingInt(Employee::getAge));

		//Collections.sort(employee, Comparator.comparing(Employee::getName).thenComparing(Employee::getDepartment).thenComparingInt(Employee::getAge));
        Collections.sort(employee, Comparator.comparing(Employee::getName));
        System.out.println("\nEmployees (Sorted by Name) : " + employee);
        
        Collections.sort(employee, Comparator.comparing(Employee::getDepartment));
        System.out.println("\nEmployees (Sorted by Department) : " + employee);
        
	}
}
