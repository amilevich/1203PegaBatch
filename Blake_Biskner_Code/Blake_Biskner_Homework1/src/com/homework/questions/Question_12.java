package com.homework.questions;

/**
 * Even Number Array Iteration
 * @author Blake Biskner
 * @version 1.12
 */

public class Question_12{
	
	public static void main(String[] args) {
		// Array Instantation and Initialization
		int hundredArray[]=new int[100];
		for(int i=1;i<=100;i++) {
			hundredArray[i-1]=i; // Initializes elements with numbers 1 through 100
		}
		
		// Printing Even Array Elements
		System.out.println("Even Array Elements");
		for(int value:hundredArray) {
			if((value%2)==0) { // If the remainder of the value divided by 2 is 0 (ie the value is even)
				System.out.print(value+" ");
			}
		}
	}
}