// Write a program to determine if an integer is even without using the modulus operator (%)

package com.example.iseven;

import java.util.Scanner;

public class IsEven {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number to check if its even: ");
		//Checking if input was of type int
		if (scanner.hasNextInt()) {
			int i = scanner.nextInt();
			//dividing and multiplying by 2 and checking if the value is the same
			if ((i / 2) * 2 == i) {
				//printing out result
				System.out.println(i+" is Even");
			} else {
				//printing out result
				System.out.println(i + " is not Even");
			}
		} else {
			System.out.println("Input has to be of typr int!");
		}
		scanner.close();
	}
}
