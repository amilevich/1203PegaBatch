package com.examples.assigment1;

import java.util.Scanner;

public class Exercise4 {
	//Q4. Write a program to compute N factorial.
	public static void main(String[] args) {
		double result=1;
		int fact =0;
		//Input an integer
		System.out.print("Factorial of: ");
		Scanner scan = new Scanner(System.in);
		
		fact = scan.nextInt();
		scan.close();
		
		//After closing the scanner go into the for and multiply each number by n(fact)
		for(int i=1; i<=fact;i++) {
			result*=i;
		}
		//Print the result
		System.out.print(" is " + result);
	}

}
