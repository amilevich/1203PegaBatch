//Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.

package com.example.primenumbers;

import java.util.ArrayList;

public class PrimeNumbers {
	public static void main(String[] args) {
		// Populating the array with 100 values
		ArrayList<Integer> list = new ArrayList<Integer>(100);
		for (int i = 1; i <= 100; i++) {
			list.add(i);
		}
		System.out.print("Prime Numbers: ");
		// Checking for prime numbers
		for (int num : list) {
			if (num == 1) {
				continue;
			} else if (num % 2 == 0 && num != 2) {
			} else if (num % 3 == 0 && num != 3) {
			} else if (num % 4 == 0 && num != 4) {
			} else if (num % 5 == 0 && num != 5) {
			} else if (num % 6 == 0 && num != 6) {
			} else if (num % 7 == 0 && num != 7) {
			} else if (num % 8 == 0 && num != 8) {
			} else if (num % 9 == 0 && num != 9) {
			} else {
				System.out.print(num + " ");
			}
		}
	}
}
