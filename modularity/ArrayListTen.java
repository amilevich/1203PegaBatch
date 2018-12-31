package com.questions.primenumbers;
//Author: Steven Jean-Paul
//Q19 - ArrayList of Ten
import java.util.ArrayList;

public class ArrayListTen {

	public static void main(String[] args) {
		ArrayList<Integer> aTen = new ArrayList<Integer>();
		Integer wrapperInts = 0;
		int prodOfWrapInts = 1;
		ArrayList<Integer> nonPrimeNums = new ArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			wrapperInts = prodOfWrapInts;
			aTen.add(wrapperInts);
			prodOfWrapInts++;
		}
		System.out.println("The list of numbers in the ArrayList are: " + aTen); //Print the entire eTen arraylist.

		//Even and odd counters are created to get the sum of each.
		int evenNums = 0;
		int oddNums = 0;
		for (int ii = 0; ii < aTen.size(); ii++) { //Visual to see even/odd #s 1 2* 3 4* 5 6* 7 8* 9 10*.
			if (aTen.get(ii) == 2) { //If statements are used to discriminate between even and odd numbers.
				evenNums += aTen.get(ii);
			}
				if(aTen.get(ii) == 3) {
					oddNums += aTen.get(ii);
				}
			if (aTen.get(ii) == 4) {
				evenNums += aTen.get(ii);
				nonPrimeNums.add(aTen.get(ii));
			}
				if(aTen.get(ii) == 5) {
					oddNums += aTen.get(ii);
				}
			if (aTen.get(ii) == 6) {
				evenNums += aTen.get(ii);
				nonPrimeNums.add(aTen.get(ii));
			}
				if(aTen.get(ii) == 7) {
					oddNums += aTen.get(ii);
				}
			if (aTen.get(ii) == 8) {
				evenNums += aTen.get(ii);
				nonPrimeNums.add(aTen.get(ii));
			}
				if(aTen.get(ii) == 9) {
					oddNums += aTen.get(ii);
					nonPrimeNums.add(aTen.get(ii));
				}
			if (aTen.get(ii) == 10) {
				evenNums += aTen.get(ii);
				nonPrimeNums.add(aTen.get(ii));

			}

		}
		System.out.println("The sum of even numbers are: " + evenNums);
		System.out.println("The sum of odd numbers are: " + oddNums);
		System.out.println("Non-prime numbers are " + nonPrimeNums);
	}
}
