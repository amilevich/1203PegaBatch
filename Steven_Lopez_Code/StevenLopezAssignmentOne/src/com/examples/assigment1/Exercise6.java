package com.examples.assigment1;

public class Exercise6 {
	//Q6. Write a program to determine if an integer is even without using the modulus operator (%)
	public static void main(String[] args) {
		int num=12; 
		double verifier=0.0;
		verifier=num/2.0; //needs to be 2.0 to get the correct fraction.
		if(verifier==num/2) { // if a fraction is found in the integer is not even
			System.out.println(num + " is in fact an even number");
		}
		else
			System.out.println(num + " is not an even number");
	}
}
