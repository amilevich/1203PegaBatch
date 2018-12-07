package com.example.javaquestions;

public class Q15 {
	// Q15. Write a program that defines an interface having the following methods:
	// addition, subtraction, multiplication, and division. Create a class that
	// implements this interface and provides appropriate functionality to carry out
	// the required operations. Hard code two operands in a test class having a main
	// method that calls the implementing class.
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 5;

		Operation operation = new Operation();

		System.out.println(operation.addition(num1, num2));
		System.out.println(operation.subtraction(num1, num2));
		System.out.println(operation.divison(num1, num2));
		System.out.println(operation.multiplication(num1, num2));

	}

}
