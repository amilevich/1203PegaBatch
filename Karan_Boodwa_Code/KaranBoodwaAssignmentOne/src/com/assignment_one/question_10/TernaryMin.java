package com.assignment_one.question_10;

// Q10. Find the minimum of two numbers using ternary operators.
public class TernaryMin {

	public static double min(double a, double b) {
		// if a < b, returns a, if a >=b, returns b
		return a < b ? a : b;
	}
	
	public static void main(String[] args) {
		double a = -70.0;
		double b = 4.2;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println("Smaller number: " + min(a,b));

	}

}
