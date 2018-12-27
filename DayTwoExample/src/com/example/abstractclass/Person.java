package com.example.abstractclass;

public abstract class Person {
	
	// abstract modifier only classes and
	// methods
	
	protected int a;
	// variables from an abstract class
	// work the same as variables
	// from a non abstract class
	
	public Person() {
		// the constructor is used
		// for initializing the data 
		// members
		// it is never invoked directly
		// the constructor can be called
		// in derived classes
	}
	
	public abstract void breathe();
	
	private void eat() {
		System.out.println("in person eat method");
	}

}
