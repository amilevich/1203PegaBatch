package com.examples.assigment1;

public class Exercise13 {
	// Q13. Display the triangle on the console as follows using any type of loop.
	// Do NOT use a simple group of print statements to accomplish this.
	// 0
	// 1 0
	// 1 0 1
	// 0 1 0 1

	public static void main(String[] args) {
		// 0 | 1 0 | 1 0 1| 0 1 0 1
		// if the "index" of the sequence is even then its a 1 if not 0
		// for the shape iterate first for for the numbers of rows and for the columns
		// use the number of rows to increase it.
		
		int iter = 1;
		int rows = 4;
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y <= x; y++) {
				if (iter % 2 == 0)
					System.out.print("1 ");
				else
					System.out.print("0 ");
				iter++;
			}
			System.out.println();
		}
	}

}
