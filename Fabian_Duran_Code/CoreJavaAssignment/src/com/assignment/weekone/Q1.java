package com.assignment.weekone;

public class Q1 {
//question 1
	static void bubbleSort (int[] array) {//takes an array input
		int length = array.length;//get the length
		int placer = 0;
		for (int i=0;i<length;i++) {//int comparison, if slot on left is greater than right, it swaps slots using placer
			for (int j=1;j<(length-i);j++) {	//relies on a double for loop
				if (array[j-1]>array[j]) {//this makes the comparison
					placer = array[j-1];//the switchroo
					array [j-1] = array[j];
					array [j] = placer;
				}
			}
		}
	}	
	
	public static void main(String[] args) {
		int assignment[] = {1,0,5,6,3,2,3,7,9,8,4};//assigned array
		System.out.println("First list is the unsorted, the second is sorted");
		for (int i = 0; i<assignment.length;i++)//displays unsorted array
			System.out.print(assignment[i]+", ");
		bubbleSort(assignment);//does that actual sorting
		System.out.println();
		for (int i = 0; i<assignment.length;i++)//displays sorted array
			System.out.print(assignment[i]+", ");
	}

}
