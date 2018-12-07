package com.homework.questions.question15;

/**
 * Operations Interface
 * @author Blake Biskner
 * @version 1.15
 */

public interface OperationalQ15{
	
	// Note each method accepts and returns double as any primitive number
	// can be assigned to a variable of type double thus the program accepts
	// any type of primitive input number
	
	/**
	 * Addition of input parameter and object value
	 * @param a is input number
	 * @return is the sum of a and object
	 */
	public double addition(double a);
	
	/**
	 * Subtraction of input parameter from object value
	 * @param a is input number
	 * @return is the difference of object value and a
	 */
	public double subtraction(double a);
	
	/**
	 * Multiplication of input parameter and input parameter value
	 * @param a is an input number
	 * @return is the product of a and object value
	 */
	public double multiplication(double a);
	
	/**
	 * Division of object value by input value
	 * @param a is input dividend
	 * @return is the quotient
	 */
	public double division(double a);
	
}
