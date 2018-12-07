package homework1.question12;

import java.util.ArrayList;

/**
 * displaying even numbers
 * @author Sergio Andujar
 * @version 1.8
 */

public class EnhancedEvenNumbers {
	
	public static void main(String[] arg) {
		
		// make a Integer ArrayList
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		// Fill up the ArrayList with 100 values
		for(int i = 1; i < 100; i++) {
			numbers.add(i);
		}
		
		// Display only the even numbers
		for(Integer even: numbers) {
			if (even % 2 == 0)System.out.println(even);
		}
	}

}
