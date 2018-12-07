package nineteen.hwk;

import java.util.ArrayList;

public class QuestionNineteen {
	/*
	 * The problem is Question Nineteen:
	 * 
	 * Create an ArrayList and insert integers 1 through 10. Display the ArrayList.
	 * Add all the even numbers up and display the result. Add all the odd numbers
	 * up and display the result. Remove the prime numbers from the ArrayList and
	 * print out the remaining ArrayList.
	 * 
	 * 
	 * 
	 */

	/*
	 * (1) To check if a number is even or odd. We can use modulus (%) all we need
	 * to do is state that some number of n % 2 == 0 -> if true is even, else is
	 * odd; Then we can just add the value to the correct variable. To get are sums
	 * of even and odd.
	 * 
	 * (2) To check to see if a number is prime all we need to do is make sure the
	 * value is not even. So, we say n % 2 == 1. This will only allow odd numbers to
	 * pass the first statement. Then we need to make sure to not allow 1 even
	 * though it's odd it's not prime. If it's modulus of 3 or 5 then it needs to
	 * be removed from the array since it's considered a prime number.
	 * 
	 * 
	 * Side note: 1(odd) is not prime but 2(even) and it's prime
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int s = 1; s <= 10; s++) {
			arr.add(s);
		}

		displayResult(arr);
	}

	public static void evenOdd(ArrayList<Integer> arr2) { // Look at (1)
		int sumEven = 0, sumOdd = 0;
		for (int x = 0; x < arr2.size(); x++) {
			if (arr2.get(x) % 2 == 0)
				sumEven += x;
			else
				sumOdd += x;
		}
		System.out.println("Sum of Even: " + sumEven);
		System.out.println("Sum of Odd: " + sumOdd);
	}

	public static ArrayList<Integer> removePrime(ArrayList<Integer> arr2) { // Look at (2)
		for (int x = 0; x < arr2.size(); x++) {
			if (arr2.get(x) % 2 == 1) {
				if (arr2.get(x) != 1) { // side note
					if (((arr2.get(x) % 3 != 0) || (arr2.get(x) % 5 != 0))) {
						arr2.remove(x);
					}
				}
			}else if (arr2.get(x) == 2) {
				arr2.remove(x);
			}
		}
		return arr2;
	}

	public static void displayResult(ArrayList<Integer> arr2) {
		System.out.println("ArrayList: " + arr2);
		evenOdd(arr2);
		System.out.println("ArrayList After Prime is removed: " + removePrime(arr2));
	}

}
