// Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4

package com.example.bubblesort;

public class BubbleSort {
	public static void main(String[] args) {
		
		//Creating the Array of size 10
		int[] array = new int[11];
		array[0] = 1;
		array[1] = 0;
		array[2] = 5;
		array[3] = 6;
		array[4] = 3;
		array[5] = 2;
		array[6] = 3;
		array[7] = 7;
		array[8] = 9;
		array[9] = 8;
		array[10] = 4;
		
		System.out.print("Unsorted Array: ");
		for (int i = 0; i<array.length;i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
		
		// Sorting the Array
		for (int i = 0; i < array.length; i++) {
				for (int j = i+1; j < array.length; j++) {
					if(array[i] > array[j]) {
						int x = array[i];
						array[i] = array[j];
						array[j] = x;
					}
				}
			}
		System.out.print("Sorted Array: ");
		for (int i = 0; i<array.length;i++) {
			System.out.print(array[i] + " ");
		}
	}
}
