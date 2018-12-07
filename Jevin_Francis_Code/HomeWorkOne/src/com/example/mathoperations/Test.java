package com.example.mathoperations;

public class Test {
	public static void main(String[] args) {
		//Hard Coded Integers
		int first = 12;
		int second = 2;
		ImplementsMath math = new ImplementsMath();

		//Checking if all functions work
		System.out.println(first + " + " + second + " = " + math.addition(first, second));
		System.out.println(first + " - " + second + " = " + math.subtraction(first, second));
		System.out.println(first + " * " + second + " = " + math.multiplication(first, second));
		System.out.println(first + " / " + second + " = " + math.dividion(first, second));
	}
}
