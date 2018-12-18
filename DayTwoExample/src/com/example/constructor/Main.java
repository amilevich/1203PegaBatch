package com.example.constructor;

public class Main {

	// this and super keywords
	// this - references the no args constructor
	// of the same class
	// super - references the constructor
	// of the parent class

	// instance variable
	int a = 20;

	// static variable
	static int b = 10;

	public void testMethod() {
		int a = 100;
		System.out.println(this.a);
	}

	// public Main() {
	// a=40;
	// }

	public static void main(String[] args) {

		Main main = new Main();
		main.testMethod();
	}

}
