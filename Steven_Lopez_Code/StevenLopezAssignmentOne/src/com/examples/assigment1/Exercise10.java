package com.examples.assigment1;

public class Exercise10 {
	// Q10. Find the minimum of two numbers using ternary operators.
	public static void main(String[] args) {

		int value1 = 129, value2 = 53, results = 0;
		//Ternary operator used by comparing value1 and value2 and printing the minimum value
		results = (value1 < value2) ? value1 : value2;
		System.out.println("The minimum between " + value1 + " and " + value2 + " is " + results);
	}
}
