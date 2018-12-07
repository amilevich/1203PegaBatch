package com.assignment.weekone;

import java.util.ArrayList;
import java.util.Collections;

public class Q7Employee {
	//parameters for employee
	String Name;
	String Department;
	int Age;
	
	public Q7Employee(String Name, String Department, int Age) {//constructor
		this.Name=Name;
		this.Department=Department;
		this.Age=Age;		
	}
	//the getters and setters
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
		return "Employee: "+Name+", "+Department+", "+Age;
	}
	public static void main(String[] args) {//bad practice, but I put main into employee because I felt weird about making so many clasess for one question
		ArrayList<Q7Employee> list = new ArrayList<Q7Employee>();//array list of employees
		list.add(new Q7Employee("Robert", "English", 67));//making
		list.add(new Q7Employee("Mary", "History", 51));
		list.add(new Q7Employee("Teresa", "Physics", 45));
		System.out.println("Unsorted");
		for (int i =0; i<list.size();i++)
			System.out.println(list.get(i));
		
		Collections.sort(list, new Q7Sortname());
		System.out.println("Sorted by Name:");
		for (int i =0; i<list.size();i++)
			System.out.println(list.get(i));
		
		Collections.sort(list, new Q7SortDept());
		System.out.println("Sorted by Department");
		for (int i =0; i<list.size();i++)
			System.out.println(list.get(i));
		
		Collections.sort(list, new Q7SortAge());
		System.out.println("Sorted by Age");
		for (int i =0; i<list.size();i++)
			System.out.println(list.get(i));


	}

}
