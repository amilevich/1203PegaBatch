package com.test.asmt01;

public class Q07Employee {
	
	String name;
	String dept;
	int age;
	
	public Q07Employee ( String name, String dept, int age ) {
		this.name = name;
		this.dept = dept;
		this.age = age;
	}
	
	public String toString() {
		return "name=" + this.name 
			 + "\tdept=" + this.dept
			 + "\tage=" + this.age
			 ;
	}

}
