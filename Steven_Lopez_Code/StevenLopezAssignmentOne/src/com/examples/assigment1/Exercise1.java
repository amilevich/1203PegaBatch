package com.examples.assigment1;

import java.util.Arrays;

public class Exercise1 {
	public static void main(String[] args) {
		
		//Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
		int numList[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		//Print unordered array and print bubble sorted array.
		System.out.println("Unordered array:     " + Arrays.toString(numList));
		System.out.print(bubbleSort(numList));
	}

	public static String bubbleSort(int[] numList) {
		boolean sorted;
		int temp = 0;
		//Stay inside the do-while until the array is sorted 
		
		//Go through the array if the next element is of higher value than the current switch places
		//and then give a value of false to the boolean variable sorted
		
		//Finally go through the least again, set the boolean to true and keep repeating this pattern until 
		// sorted does get to keep the true value by not going inside the if condition block.
		do {
			sorted = true;
			for (int idx = 0; idx < (numList.length - 1); idx++) {
				if (numList[idx] > numList[idx + 1]) {
					temp = numList[idx];
					numList[idx] = numList[idx + 1];
					numList[idx + 1] = temp;
					sorted = false;
				}
			}
		} while (!sorted);
		
		return "Bubble Sorted array: " + Arrays.toString(numList);
	}
}
