package com.questions.comparator;
//Steven Jean-Paul
//Q7 - Comparator
public class Employee implements Comparable<Employee>{ //Employee class implements the Comparable interface.
	private String name;
	private String department;
	private Integer age;

	public Employee() {}

	public Employee(String department, String name, int age) { //Setup a constructor that uses all three private class fields.
		super();
		this.department = department;
		this.name = name;
		this.age = age;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	public String getDepartment(){
		return this.department;
	}


	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}

	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}

	@Override 
	public String toString() {
		return "Employee " + name + " is in " + department + " department and is " + age + " years old.";
	}
	
	@Override
	public int compareTo(Employee e){
		 //this.age - e.age
		 
		 return this.age - e.age;
	}

}
