package com.homework1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Use 'Collections' to implement the 'unnatural' sorting

public class Question7 implements Comparator<Employee> {
	
	public static void main(String[] args) {
		
		ArrayList<Employee> workers = new ArrayList<>();
		
		workers.add(new Employee("Adam", "Sales", 18));
		workers.add(new Employee("Bilbo", "Sales", 20));
		workers.add(new Employee("Bilbo", "Development", 33));
		workers.add(new Employee("Bilbo", "Development", 22));
		
		Collections.sort(workers, new Question7());;
		System.out.println(workers.toString());
	}

	/*
	 * Employees are first sorted by name, then department, then age
	 * 
	 */
	@Override
	public int compare(Employee o1, Employee o2) {
		
		// If the names are identical
		if (o1.getName().compareTo(o2.getName()) == 0) {
			
			// if the department is identical
			if (o1.getDepartment().compareTo(o2.getDepartment()) == 0) {
				
				// if the ages are the same
				if (o1.getAge() == o2.getAge()) {
					return 0;
				} else if (o1.getAge() > o2.getAge()){
					return 1;
				} else {
					return -1;
				}
			} else if (o1.getDepartment().compareTo(o2.getDepartment()) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (o1.getName().compareTo(o2.getName()) > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}
