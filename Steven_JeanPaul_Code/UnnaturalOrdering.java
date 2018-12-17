package com.questions.comparator;
//Author: Steven Jean-Paul
//Q7 - Comparator
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnnaturalOrdering {
	public static void main(String[] args){
		
		List<Employee> employee = new ArrayList<>(); //Setup a generic
		employee.add(new Employee("Physics", "Harvey", 22)); //Enter data a new employee object in the dynamic list.
		employee.add(new Employee("Mathematics", "Batman", 44));

		
		printListPretty(employee); //Uses an overriden method to make the employee's data display in a clean format
		Collections.sort(employee);
		printListPretty(employee);
		Collections.sort(employee, new EmployeeDepartmentComparator()); //Set and order an employee object
		printListPretty(employee);
		System.out.println("Departments have been sorted.");
		
		printListPretty(employee);
		Collections.sort(employee);
		printListPretty(employee);
		Collections.sort(employee, new EmployeeNameComparator());
		printListPretty(employee);
		System.out.println("Names have been sorted.");
		
		printListPretty(employee);
		Collections.sort(employee);
		printListPretty(employee);
		Collections.sort(employee, new EmployeeAgeComparator());
		printListPretty(employee);
		System.out.println("Ages have been sorted");
	}

	static void printListPretty(List<Employee> list){
		//System.out.println("Employee: ");
		for(Employee emp : list){
			System.out.println(emp);
		}
		System.out.println();
	}

}
