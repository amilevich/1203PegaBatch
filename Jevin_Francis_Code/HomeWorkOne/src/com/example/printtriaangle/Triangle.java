//  Display the triangle on the console as follows using any type of loop.  
//  Do NOT use a simple group of print statements to accomplish this.  
//	0 
//	1 0
//	1 0 1
//	0 1 0 1

package com.example.printtriaangle;

public class Triangle {

	public static void main(String[] args) {
		int i = 0;
		int l = 1;
		// Two for loops to go down and right
		for (int x = 0; x < 4; x++) {
			for (int y = l; y > 0; y--) {
				System.out.print(i + " ");
				if (i == 0) {
					i = 1;
				} else {
					i = 0;
				}
			}
			l++;
			System.out.println("");
		}

	}
}
