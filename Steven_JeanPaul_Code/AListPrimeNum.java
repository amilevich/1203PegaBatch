package com.questions.primenumbers;
//Author: Steven Jean-Paul
//Q9 - Prime Numbers
import java.util.ArrayList;

public class AListPrimeNum {

	public static void main(String[] args) {
		// int num = 0;
		boolean checker = false;
		ArrayList<Integer> primeNum = new ArrayList<Integer>(); //Create an arraylist to store int objects
		ArrayList<Integer> primeHolder = new ArrayList<Integer>(); //Create arraylist to hold prime numbers.

		for (int i = 2; i < 101; i++) { // store numbers 1 to 100.
			primeHolder.add(i);

		}


		for (int n : primeHolder) {
			checker = false; 				//Use checker as a trigger to dictate which numbers are deemed prime.
			for (int k = 2; k < n; k++) {

				if (n % k == 0) { 
					checker = true;
				}
			}
			if(!checker) {
				primeNum.add(n);
			}
		}
		System.out.println(primeNum); //Print all prime numbers in the primeNum arraylist.

	}
}
