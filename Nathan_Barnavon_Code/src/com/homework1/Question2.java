package com.homework1;

public class Question2 {
	
	public static void printFibs(int toNum) {
		
		// Make sure to handle edge cases of toNum = 1 or 2
		int[] trackFibs = new int[toNum+2];
		trackFibs[0] = 0;
		trackFibs[1] = 1;
		
		if (toNum > 0) {
			System.out.print(trackFibs[0] + ", ");
		}
		if (toNum > 1) {
			System.out.print(trackFibs[1] + ", ");
		}
		
		// loop through the remaining numbers to properly calculate the fibonacci sequence
		for(int i=2; i<toNum; i++) {
			trackFibs[i] = trackFibs[i-1] + trackFibs[i-2];
			System.out.print(trackFibs[i] + ", ");
		}	
	}
	
	public static void main(String[] args) {
		printFibs(25);
	}
}
