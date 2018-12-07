package com.homework.questions;

/**
 * @author Blake Biskner
 * @version 1.6
 *
 */

public class Question_6{
	
	/** Tests if even for the quotient of an even int and two will be the same for integer and regular division;
	 * however, the quotient of these operations will be different by 0.5 for an odd integer
	 * @param num is the number to be tested
	 * @return boolean returned indicating true if even and false otherwise
	 */
	public static boolean evenTest(int num) {
		return((num/2)==(num/2.0));
	}
	public static void main(String[] args) {
		int num=8;
		if(evenTest(num)) {
			System.out.println("Even");
		} else {
			System.out.println("Odd");
		}
	}
}