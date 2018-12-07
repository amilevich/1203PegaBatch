package com.example.reflection;

public class Bilboh {
	
	private String s;
	
	public Bilboh() {
		s="Mark (question?)";
	}

	public void method1() {
		System.out.println("The string is: " + s);
	}
	
	public void method2(int a) {
		System.out.println("The number is " + a);
	}
	
	private void method3() {
		System.out.println("Private method invoked");
	}
}
