package com.homework.q1;

public class Main {

	public static void main(String[] args) {
		
		// Array of unsorted integers
		int x[] = {1,0,5,6,3,2,3,7,9,8,4};
		
		// Displays the elements of the array before being sorted
		for(int i = 0; i < x.length; i++ ) {
			System.out.print(x[i] + " ");      
		}
		
		bubbleSort(x);
		
		System.out.println();
		
		// Displays the elements of the array after being sorted
		for(int i = 0; i < x.length; i++ ) {
			System.out.print(x[i] + " ");      
		}
	}
	
	
	static void bubbleSort(int[] arr) {
		
		boolean swapped = true;
		int temp = 0;
		while(swapped)
		{
			swapped = false;
			
			for(int i = 1; i < arr.length; i++) {
				if(arr[i-1] > arr[i]) {
					
					temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
					swapped = true;
				}
			}
		}
	}
}
