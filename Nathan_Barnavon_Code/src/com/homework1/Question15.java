package com.homework1;

public class Question15 implements Question15Interface {
	
	@Override
	public int addition(int num1, int num2) {
		return num1 + num2;
	}

	/**
	 * @param num1: The number to subtract from
	 * @param num2: The number to subtract
	 */
	@Override
	public int subtraction(int num1, int num2) {
		return num1 - num2;
	}

	@Override
	public int multiplication(int num1, int num2) {
		return num1 * num2;
	}

	/**
	 * Should not divide by zero
	 * @param num1: The number to divide from
	 * @param num2: The number to divide
	 */
	public double division(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return num1 / num2;
	}

}
