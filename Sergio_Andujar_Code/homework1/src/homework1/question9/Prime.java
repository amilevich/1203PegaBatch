package homework1.question9;

import java.util.ArrayList;

/**
 * Prime detector
 * @author Sergio Andujar
 * @version 1.8
 */

public class Prime {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for(int i = 1; i < 100; i++) {
			numbers.add(i);
		}
		
		for(Integer number: numbers) {
			if(isPrime(number)) System.out.println(number);
		}
		
	}

	public static boolean isPrime(Integer number) {
		
		// 0 and 1 are not prime numbers
		if(number == 0)return false;
		if(number == 1)return false;
		// starting at 2 since 2 is a prime number
		for(int i = 2; i<number; i++) {
			if(number % i == 0) return false;
		}
		return true;
	}
		
}
