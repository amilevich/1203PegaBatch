package com.homework.q4;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num = 0;
		
		Scanner scan = new Scanner(System.in); 
		num = scan.nextInt();
		scan.close();
		
		System.out.println("Factorial of " + num + " is " + factorial(num) );
		

	}

	public static int factorial(int num) {
		
		if(num > 1) {
			return num * factorial(num - 1);
		}
		
		else {
			return 1;
		}
		
	}

}
