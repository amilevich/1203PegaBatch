package com.assignment.weekone;

public class Q2 {
	//Fibonacci's numbers adds previous two numbers to make next number
	static void fibonacciMaker (int[] array) {//will do fibonacci number array of size n
		for (int i = 0;i<array.length;i++) {
			if (i==0)//secures first value equals 0
				array[i]=i;
			else if (i == 1)//secures second value equals 1
				array [i] = i;
			else			
			array[i]=array [i-1]+array[i-2];//to start actual number sequence
		}
	}
	public static void main(String[] args) {
		int fibNum [] = new int [25];//make array of size 25
		fibonacciMaker(fibNum);//populate array
		System.out.println("The first 25 number sequence is :");
		for (int i = 0; i < fibNum.length;i++)
			System.out.print(fibNum[i]+", ");//to display the numbers

	}

}
