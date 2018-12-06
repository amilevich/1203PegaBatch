package com.assignment_one.question_1;

// Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
public class BubbleSort {

	
	// Bubble Sort:
	// bubblesort() continuously swaps adjacent members of the given array if 
	// they are not in ascending order, eventually resulting in a sorted array
	public static void bubbleSort(Integer[] arrayToSort) {
		
		// flag that is used to determine if the array is sorted
		boolean sorted = false;
		
		while (!sorted) {
			// flag is initially set to true. If any swap occurs, this flag
			// will be set to false, as this indicates that the array is 
			// not yet sorted
			// once a full pass of the array is done without any swapping
			// the array will be sorted
			sorted = true;
			for (int i = 0; i < (arrayToSort.length-1); i++) {
				
				// Currently sorts in ascending order
				// change to < to sort in descending order
				if( arrayToSort[i] > arrayToSort[i+1]) {
					// XOR Swap
					arrayToSort[i]   = arrayToSort[i] ^ arrayToSort[i+1];
					arrayToSort[i+1] = arrayToSort[i] ^ arrayToSort[i+1];
					arrayToSort[i]   = arrayToSort[i] ^ arrayToSort[i+1];
					// sorted set to false as a swap has occurred this iteration
					sorted = false;
				}
			}
		}
	}

	public static void main(String[] args) {

		// arrayToSort is the input array to the bubblesort method
		Integer arrayToSort[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

		bubbleSort(arrayToSort);

		for (Integer i : arrayToSort) {
			System.out.println(i);
		}

	}

}
