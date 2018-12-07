package com.homework.questions;

/**
 * Ternary Operator
 * @author Blake Biskner
 * @version 1.9
 */

public class Question_10 {
	
	public static void main(String[] args) {
		double num1=100;
		double num2=101;
		if(num1==num2) {
			throw new IllegalArgumentException();
		}
		else {
			System.out.print("Minimum of "+num1+" and "+num2+" is ");
			System.out.println(findMin(num1, num2));
		}
		
	}
	
	/**
	 * Method to determine minimum of two provided numbers (can be of any type ie int, float, etc and will be automatically assigned to double
	 * without losing byte information)
	 * @param a is the first input number double since each primitive number value can be assigned to a double variable
	 * @param b is the second input number double since each primitive value can be assigned to a double variable
	 */
	public static double findMin(double a, double b) {
		double result=(a<b)?a:b;
		// If condition true (ie a is less than b) return a is minimum else return b is minimum
		return result;
	}
}
