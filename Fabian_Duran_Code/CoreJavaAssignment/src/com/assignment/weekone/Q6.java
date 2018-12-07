package com.assignment.weekone;

public class Q6 {
	static boolean isEven(int n) {
		boolean isEven = true;
		for (int i = 1; i <= n; i++)//flips back and forth between true and false, if even is Even will stay even, and odd becomes false
			isEven = !isEven;
		return isEven;
	}
	public static void main(String[] args) {
		int number =  4;
		System.out.println("Is "+number+" even? -> "+isEven(number));//to test even number
		number =  5;
		System.out.println("Is "+number+" even? -> "+isEven(number));//to test odd number
	}

}
