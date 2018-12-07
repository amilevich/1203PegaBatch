package com.test.asmt01;

public class Q15Operations implements Q15Operational {

	@Override
	public int addition(int a, int b) {
		return a + b;
	}

	@Override
	public int subtraction(int a, int b) {
		return a - b;
	}

	@Override
	public int multiplication(int a, int b) {
		return a * b;
	}

	@Override
	public double division(int a, int b) {
		return (double) a / b;
	}
	
	

}
