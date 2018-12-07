package com.examples.assigment1;

import java.util.ArrayList;

public class Exercise2 {
	// Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
	public static void main(String[] args) {
		int max=25;
		System.out.println("The fibonacci sequence till "+max+" is " + (fib(25).toString()));
	}

	public int fibRec(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;
		return fibRec(n - 1) + fibRec(n - 2);
	}
	
	
	//A more efficient way of calculating the fibonacci sequence.
	public static ArrayList<Integer> fib(int n) {
		ArrayList<Integer> fibMem = new ArrayList<Integer>();//use a mutable list
		fibMem.add(0);//add the basic seeds (0,1)
		fibMem.add(1);

		for (int i = 2; i < n; i++) {
			fibMem.add(fibMem.get(i - 1) + fibMem.get(i - 2));
			//After you calculate a fib sequence not present in the list, add it.
			//Calculate only using numbers in the ArrayList, and prevent waste of time in recursion.
		}
		return fibMem;
	}
}
