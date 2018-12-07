package com.homework1;

public class Question12 {

	public static void main(String[] args) {
		
		int n = 100;
		int[] nums = new int[n];

		// loop through 0-100 and add into the array
		for (int i=0; i<n; i++) {
			nums[i] = i+1;
		}
		
		// loop through all numbers in the array and check if it's even
		for (int num : nums) {
			if (num % 2 == 0) {
				System.out.print(num + ", ");
			}
		}
	}
}
