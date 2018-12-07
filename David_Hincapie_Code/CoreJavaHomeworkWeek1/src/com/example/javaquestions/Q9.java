package com.example.javaquestions;

import java.util.ArrayList;

public class Q9 {
	// Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all
	// the prime numbers to the console.
	public static void main(String[] args) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (int i = 1; i < 101; i++) {
			numbers.add(i);
		}
		for (Integer integer : numbers) {
			System.out.print(integer + " ");
		}

		for (int i = 0; i <= numbers.size() - 1; i++) {
			if (!isPrime(numbers.get(i))) {
				numbers.remove(numbers.get(i));
				i--;
			}
		}

		System.out.println();
		for (Integer integer : numbers) {
			System.out.print(integer + " ");
		}
	}

	public static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
