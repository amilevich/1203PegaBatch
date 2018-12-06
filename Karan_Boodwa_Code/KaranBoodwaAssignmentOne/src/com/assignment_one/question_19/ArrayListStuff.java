package com.assignment_one.question_19;

import java.util.ArrayList;

/*
 * Q19. Create an ArrayList and insert integers 1 through 10. 
 * Display the ArrayList. 
 * Add all the even numbers up and display the result. 
 * Add all the odd numbers up and display the result. 
 * Remove the prime numbers from the ArrayList and print out the remaining ArrayList.
 */
public class ArrayListStuff {

	/*
	 * isPrime checks if a given number is prime by checking its remainder after
	 * division against all integers in the range [2,x/2] if it doesn't divide into
	 * any number up to half, it is prime since mathematically its first divisor
	 * would have to fall in this range
	 */
	public static boolean isPrime(int x) {
		if (x == 1) {
			return false;
		}
		for (int i = 2; i <= x / 2; i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();

		// Inserting integers 1 - 10 into the ArrayList
		for (int i = 1; i <= 10; i++) {
			al.add(i);
		}

		// Displaying the ArrayList
		System.out.print("[ ");
		for (int i : al) {
			System.out.print(i + " ");
		}
		System.out.println("]");

		// Adding up all the even numbers
		int evenSum = 0;
		for (int i : al) {
			// Even = divisible by 2 = remainder of 0 when dividing by 2
			if (i % 2 == 0) {
				evenSum += i;
			}
		}
		// Displaying the result:
		System.out.println("Sum of Even numbers: " + evenSum);

		// Adding up all the even numbers
		int oddSum = 0;
		for (int i : al) {
			// Odd = not divisible by 2 = remainder of 1 when dividing by 2
			if (i % 2 == 1) {
				oddSum += i;
			}
		}
		// Displaying the result:
		System.out.println("Sum of Odd numbers: " + oddSum);

		// Removing all prime numbers from the Array List
		for (int i = 0; i < al.size(); i++) {
			// if a prime number is encountered, it's removed and i is decremented
			// to account for the now modified ArrayList
			if (isPrime(al.get(i))) {
				al.remove(i--);
			}
		}
		// Displaying the result
		System.out.print("Non-prime Numbers: [ ");
		for (int i : al) {
			System.out.print(i + " ");
		}
		System.out.println("]");

	}

}
