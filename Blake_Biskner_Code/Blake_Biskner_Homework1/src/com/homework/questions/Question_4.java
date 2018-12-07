package com.homework.questions;

/** Factorial Calculator
 * 
 * @author Blake Biskner
 * @version 1.4
 * 
 */

public class Question_4{
		
	public static void main(String[] args) {
		// Variable Initialization
		int num=5;
		int factNum=1;
		
		// Variable Validation
		if(num==0) {
			num=1;
		} else if(num<0) {
			System.out.println("Factorial only applicable to nonnegative integers. Default value is 1. Please enter valid number");
			num=1;
		} else { // Just have else for convention
		}
		
		for(int i=num;i>1;i--) {
			factNum*=i;
		}
		System.out.println(factNum);
	}
}
