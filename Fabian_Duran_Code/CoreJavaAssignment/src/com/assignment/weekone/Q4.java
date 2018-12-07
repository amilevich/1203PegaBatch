package com.assignment.weekone;

public class Q4 {
	static int factorial (int n){
		int p= n;//place holder for original number
		for (int i = 1; i<p;i++) {
			n=n*i;//does that actual factorial
			//System.out.println(n); -> to test progress
		}
		return n;
		
	}
	public static void main(String[] args) {
		int number = 5;
		number = factorial(number);//do the question here
		System.out.println(number);//show the work!
	}

}
