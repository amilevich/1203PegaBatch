package com.examples.assigment1;

public class Exercise12 {
	// Q12. Write a program to store numbers from 1 to 100 in an array. Print out
	// all the even numbers from the array. Use the enhanced FOR loop for printing
	// out the numbers.

	public static void main(String[] args) {
		int arr[] = new int[100];
		//default for-loop used to store values 1-100 into the arr array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		//enhanced for-loop used to print all even numbers in the array by using %(mod)
		System.out.println("List of even numbers: ");
		for (int num : arr) {
			if (num % 2 == 0)
				System.out.println(num);
		}
	}
}
