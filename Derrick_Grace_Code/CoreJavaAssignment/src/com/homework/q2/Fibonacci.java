package com.homework.q2;

public class Fibonacci {

	public static void main(String[] args) {
		
		int fibonacci = 0;
		int fibonacci2 = 1;
		
		for(int i = 0; i < 25; i++) {
			System.out.print(fibonacci + " ");
			fibonacci += fibonacci2;
			fibonacci2 = fibonacci - fibonacci2;
		}
		
	}

}
