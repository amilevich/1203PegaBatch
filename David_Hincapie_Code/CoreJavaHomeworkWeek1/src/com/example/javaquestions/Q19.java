package com.example.javaquestions;

import java.util.ArrayList;

public class Q19 {
	// Q19. Create an ArrayList and insert integers 1 through 10. Display the
	// ArrayList. Add all the even numbers up and display the result. Add all the
	// odd numbers up and display the result. Remove the prime numbers from the
	// ArrayList and print out the remaining ArrayList.
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		int evenSum = 0;
		int oddSum = 0;

		for (int i = 1; i < 11; i++) {
			arrayList.add(i);

		}

		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i) % 2 == 0) {
				evenSum += arrayList.get(i);
			} else {
				oddSum += arrayList.get(i);
			}
			if (isPrime(arrayList.get(i))) {
				arrayList.remove(i);
			}

		}

		for (Integer integer : arrayList) {
			System.out.print(integer + " ");
		}
		System.out.println("\nEven sum: " + evenSum + "\nOdd sum: " + oddSum);
		for (Integer integer : arrayList) {
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
