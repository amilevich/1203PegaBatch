package com.test.asmt01;

public class Q01 {

	public static void main(String[] args) {

		int[] arr = new int[] {1, 10, 2, 9, 3, 7, 4, 6, 5}; 
		
		System.out.println("Before Sort");
		
		for ( int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		arr = sortTheArray(arr);
		
		System.out.println("After Sort");
		
		for ( int i=0; i<arr.length; i++ ) {
			System.out.println(arr[i]);
		}
		
	}
		
	public static int[] sortTheArray(int[] arr) {
		
		for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
		
		return arr;

	}

}
