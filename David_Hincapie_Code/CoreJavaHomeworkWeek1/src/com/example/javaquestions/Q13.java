package com.example.javaquestions;

public class Q13 {
	// Q13. Display the triangle on the console as follows using any type of loop.
	// Do NOT use a simple group of print statements to accomplish this.
	// 0
	// 1 0
	// 1 0 1
	// 0 1 0 1

	public static void main(String[] args) {
		int num = 1;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i; j++) {
				if (num == 0) {
					System.out.print("1");
					num = 1;
				} else if (num == 1) {
					System.out.print("0");
					num = 0;
				}

			}
			System.out.println();
		}

	}

}
