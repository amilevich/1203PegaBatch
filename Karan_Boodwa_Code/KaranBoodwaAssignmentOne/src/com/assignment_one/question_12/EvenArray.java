package com.assignment_one.question_12;

// Q12. Write a program to store numbers from 1 to 100 in an array. 
// Print out all the even numbers from the array. 
// Use the enhanced FOR loop for printing out the numbers.

public class EvenArray {

	public static void main(String[] args) {
		// Integer array nums created to hold numbers 1 to 100
		Integer nums[] = new Integer[100];

		// Stores numbers 1 to 100 in nums
		for (int i = 0; i < 100; i++) {
			nums[i] = i+1;
		}

		for (int i : nums) {
			// Prints i if it's even (divisible by 2)
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}

	}

}
