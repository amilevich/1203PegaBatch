package com.assignment_one.question_15;

/* Q15. Write a program that defines an interface having the following methods: 
 * addition, subtraction, multiplication, and division. 
 * Create a class that implements this interface and provides appropriate functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that calls the implementing class.
 */

// math interface with 4 basic arithmetic methods
interface math {
	double addition(double a, double b);
	double subtraction(double a, double b);
	double multiplication(double a, double b);
	double division(double a, double b);
}

// Implements the above interface by defining/implementing the 4 abstract methods
class Calculator implements math {
	
	// Self-explanatory mathods (heh)

	@Override
	public double addition(double a, double b) {
		
		return a+b;
	}

	@Override
	public double subtraction(double a, double b) {
		
		return a-b;
	}

	@Override
	public double multiplication(double a, double b) {
		
		return a*b;
	}

	@Override
	public double division(double a, double b) throws ArithmeticException{
		if(b == 0) {
			throw new ArithmeticException();
		}
		else {
			return a/b;
		}
	}

}

// Test class:
public class InterfaceImplementation {
	public static void main(String[] args) {
		// To illustrate error handling, b is hard coded to 0 as the only interesting case
		double a = 5.0;
		double b = 0.0;
		
		System.out.println(a + "+" + b + "=" + new Calculator().addition(a, b));
		System.out.println(a + "-" + b + "=" + new Calculator().subtraction(a, b));
		System.out.println(a + "*" + b + "=" + new Calculator().multiplication(a, b));
		
		try {
			System.out.println(a + "/" + b + "=" + new Calculator().division(a, b));
		} catch (Exception e) {
			System.err.println("Can't divide by 0.");
		}
		
	}
}
