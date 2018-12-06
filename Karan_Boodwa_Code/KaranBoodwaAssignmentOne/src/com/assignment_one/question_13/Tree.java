package com.assignment_one.question_13;

/* Q13. Display the triangle on the console as follows using any type of loop.  
 * Do NOT use a simple group of print statements to accomplish this.  
 * 0 
 * 1 0
 * 1 0 1
 * 0 1 0 1
 */

public class Tree {

	public static void main(String[] args) {

		// Nested for loop solution
		// Note: the overall pattern is just alternating 1s and 0s
		// so maintaining a count between lines and printing count%2 
		// results in the desired pattern
		int count = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < i+1; j++) {
				System.out.print(count%2);
				count++;
			}
			System.out.print("\n");
		}

	}

}
