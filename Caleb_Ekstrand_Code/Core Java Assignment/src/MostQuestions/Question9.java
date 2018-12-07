package MostQuestions;

import java.util.ArrayList;

public class Question9 {

	public static void main(String[] args) {
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		for (int i = 0; i <= 100; i++) {
			arrlist.add(i);
		}
		//System.out.println(1 % 2);
		ArrayList<Integer> arrlist2 = new ArrayList<Integer>();
		// get only prime nums
		for (Integer i : arrlist) {
			if (isPrime(i)) {
				arrlist2.add(i);
			}
		}
		System.out.println(arrlist2);
	}

	public static boolean isPrime(Integer check) {
		if (check > 1) { // one is not prime or composite
			if ((check == 2) || (check == 3) || (check == 5)) { // these are prime but will also be used to check for
																// primes so we have to detect them separately
				return true;
			} else {
				if ((check % 2 != 0) && (check % 3 != 0) && (check % 5 != 0)) { // primes will not be divisible by
																				// anything... which can be broken down
																				// into 2, 3, or 5
					return true;
				}
			}
		}
		return false;
	}

}
