package com.homework1;

public class Question1 {

	public static void bubbleSort(int[] arr) {

		// bubble sort requires a full iteration of the array for each element
		for (int i = 0; i < arr.length; i++) {
			
			// start index at 1 to handle index out of bounds case
			for (int j = 1; j < arr.length; j++) {
				
				// swap the values if the number to the right is smaller than
				// the number to the left
				if (arr[j] < arr[j-1]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		bubbleSort(arr);
		
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
	}

}
