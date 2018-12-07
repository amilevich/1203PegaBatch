package com.examples.assigment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Exercise7 {
	// Q7. Sort two employees based on their name, department, and age using the
	// Comparator interface.
	public static class Employee {
		private String name;
		private String department;
		private int age;

		public Employee(String name, String department, int age) { 
			this.setName(name);
			this.setDepartment(department);
			this.setAge(age);
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
		
	    public String toString() 
	    { 
	        return this.name + " " + this.department + 
	                           " " + this.age; 
	    } 

	}	
	//Sort class that implements the Comparator class with generic <Employee>
	//used to sort employees by name by using the .compareTo() method
	static class SortbyName implements Comparator<Employee>{
		public int compare(Employee emp1, Employee emp2) {
			return emp1.name.compareTo(emp2.name);
		}
	}
	//Sort class that implements Comparator class with generic <Employee>
	//used to sort employees by Department by using the .compareTo() method
	static class SortbyDepartment implements Comparator<Employee>{
		public int compare(Employee emp1, Employee emp2) {
			return emp1.department.compareTo(emp2.department);
		}
	}
	//Sort class that also implements Comparator class with generic <Employee>
	//used to sort employees by age by using the .compareTo() method
	static class SortbyAge implements Comparator<Employee>{
		public int compare(Employee emp1, Employee emp2) {
			return emp1.age - emp2.age;
		}
	}
	static class Main{
		public static void main(String[] args) {
			//Declare two employees and instantiate them.
			ArrayList<Employee> emps = new ArrayList<Employee>();
			emps.add(new Employee("Hamsolo", "Jelly", 234));
			emps.add(new Employee("Zeff", "Cullinary Arts", 1));
			
			//Print out an unsorted list
			System.out.println("Unsorted");
			for(int i=0; i<emps.size();i++) {
				System.out.println(emps.get(i));
			}
			//List of employees sorted by name
			System.out.println("\nSorted by Name");
			Collections.sort(emps, new SortbyName());
			for(int i=0; i<emps.size();i++) {
				System.out.println(emps.get(i));
			}
			
			//List of employees sorted by Department
			System.out.println("\nSorted by Department");
			Collections.sort(emps, new SortbyDepartment());
			for(int i=0; i<emps.size();i++) {
				System.out.println(emps.get(i));
			}
			
			//List of employees sorted by age
			System.out.println("\nSorted by Age");
			Collections.sort(emps, new SortbyAge());
			for(int i=0; i<emps.size();i++) {
				System.out.println(emps.get(i));
			}
		}
	}
	
}
