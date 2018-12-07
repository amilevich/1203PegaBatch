package com.example.javaquestions;

public class Q1 {
	// Q1. Perform a bubble sort on the following integer array:
	// 1,0,5,6,3,2,3,7,9,8,4

	public static void main(String[] args) {

		int[] numbers = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

		//store length of array so you could change value
		int length = numbers.length;

		//iterate through array length value times  
		for (int i = 0; i < length - 1; i++)

			for (int j = 0; j < length - i - 1; j++)

				//checks if current number us greater than next number
				if (numbers[j] > numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}

		//for each to iterate through array and print numbers
		for (int number : numbers) {
			System.out.print(number + ", ");
		}

	}
}
