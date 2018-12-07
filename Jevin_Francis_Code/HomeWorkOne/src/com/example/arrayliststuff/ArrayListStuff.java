//Create an ArrayList and insert integers 1 through 10. 
//Display the ArrayList. Add all the even numbers up and display the result. 
//Add all the odd numbers up and display the result. Remove the prime numbers from the 
//ArrayList and print out the remaining ArrayList.

package com.example.arrayliststuff;

import java.util.ArrayList;

public class ArrayListStuff {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		// Populating the ArrayList with numbers from 1 - 10
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}

		Integer evenSum = 0;
		for (Integer num : list) {
			// Checking if the number is Even
			if (num % 2 == 0) {
				// If the number is even adding the value to evenSum
				evenSum += num;
			}
		}
		// Printing out the final sum of Even Numbers
		System.out.println("Sum of All Even Number is: " + evenSum);

		Integer oddSum = 0;
		for (Integer num : list) {
			// Checking if number is odd
			if (num % 2 != 0) {
				// If the number is odd adding the value to oddSum
				oddSum += num;
			}
		}
		// Printing out the final sum of Odd Numbers
		System.out.println("Sum of All Odd Number is: " + oddSum);

		ArrayList<Integer> prime = new ArrayList<Integer>();
		// Create a list of Prime Numbers
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
				prime.add(num);
			}
		}
		for (Integer i : prime) {
			// removing all the prime number from the ArrayList list
			list.remove(i);
		}
		//Printing the list with Prime numbers removed
		System.out.println("List With Prime Numbers Removed: " + list);
	}
}
