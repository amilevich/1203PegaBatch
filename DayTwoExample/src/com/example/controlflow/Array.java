package com.example.controlflow;

public class Array {

	// what is an array?
	// group of sequentially stored elements
	// of the same type
	// size is fixed
	// when you initialize an array, all elements
	// are set to the default value for that
	// data type

	public static void main(String[] args) {

		int tempOne[] = { 1, 2, 3, 4 };
		// System.out.println(tempOne.length);
		// .length - size of the array

		int tempTwo[] = new int[9004];

		tempOne[1] = 50;
		// System.out.println(tempOne[3]);

		boolean tempThree[] = new boolean[63124];
		// default is false

		int tempFour[][] = { { 9, 10, 11 }, { 4, 2, 7 }, { 6, 6, 5 } };

		// int tempFive[][][][][][][][][][];
		// totally possible

		int tempSix[][] = new int[10][12];
		tempSix[5][8] = 80;

		int tempSeven[] = new int[100];
		tempSeven[8] = 90;
		// tempSeven[200] = 100;
		// System.out.println(tempSeven[200]);

		int tempEight[];
		// System.out.println(tempEight[1]);
		tempEight = new int[10];
		System.out.println(tempEight.length);
		
	

		// out of bounds
		
		
		//arraylists - do not have a fixed size


	}

}
