package com.questions.ternary;

//Author: Steven Jean-Paul
//Q10 - Minimum value using Ternary Operator

public class Minimum {

	public static void main(String[] args) {
		int a = 9;
		int b = 2;
		int min = 0;
		
		min = (b > a )?(min = a):(min = b); //Ternary operator is assigned to variable min. 
										    //If b > a then a is assigned to min.
		
		System.out.println("The minimum value is: " + min); //Print min.

	}

}
