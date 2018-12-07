package com.examples.assigment1;

import java.util.Scanner;

public class Exercise15 {
	// Q15. Write a program that defines an interface having the following methods:
	// addition, subtraction, multiplication, and division. Create a class that
	// implements this interface and provides appropriate functionality to carry out
	// the required operations. Hard code two operands in a test class having a main
	// method that calls the implementing class.
	public static void main(String[] args) {
		Exercise15 e = new Exercise15();
		double num1=0, num2=0;
		boolean numeric = true;
		Calculator calc = e.new Calculator();
		Scanner scan1 = new Scanner(System.in);
		//do-while do validate for correct input data from the user
		do {
			numeric = true;
			try {
				System.out.print("First Value: ");
	            num1 = Double.parseDouble(scan1.next());
	        } catch (NumberFormatException ex) {
	            numeric = false;
	            System.out.println("Error: Invalid input!");
	        }
		} while (!numeric);
			
		////do-while do validate for correct input data from the user
		do {
			numeric = true;
			try {
				System.out.print("Second Value: ");
	            num2 = Double.parseDouble(scan1.next());
	        } catch (NumberFormatException ex) {
	            numeric = false;
	            System.out.println("Error: Invalid input!");
	        }
			if(num2==0 )
				System.out.println("Error: Second value(Divisor) can't be zero!");
		} while (num2==0 || !numeric);
		
		scan1.close();
		//Call all the methods to print the results
		System.out.println("The addition is " + calc.addition(num1, num2));
		System.out.println("The subtraction is " + calc.subtraction(num1, num2));
		System.out.println("The multiplication is " + calc.multiplication(num1, num2));
		System.out.println("The division is " + calc.division(num1, num2));
	}
	//Interface with various abstract methods
	public interface Calculate {
		public double addition(double v1, double v2);

		public double subtraction(double v1, double v2);

		public double multiplication(double v1, double v2);

		public double division(double v1, double v2);
	}
	//Calculator class that implements the interface Calculate
	public class Calculator implements Calculate {
		//Addition method that add the two values pass as parameter
		@Override
		public double addition(double v1, double v2) {
			return v1 + v2;
		}
		//Subtraction method that subtracts the two values pass as parameter
		@Override
		public double subtraction(double v1, double v2) {
			return v1 - v2;
		}
		//Multiplication method that multiplys the two values pass as parameter
		@Override
		public double multiplication(double v1, double v2) {
			return v1 * v2;
		}

		/**
		 * The divisor can't be zero or an IllegalArgumentException will be thrown
		 * 
		 * @param v2
		 * @throws Exception
		 */
		@Override
		public double division(double v1, double v2) {
			if (v2 == 0.0)
				return -0;
			else
				return v1 / v2;
		}
	}
}
