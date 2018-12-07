package com.example.mathoperations;

public class ImplementsMath implements MathOperations {
	// implementing addition
	@Override
	public int addition(int first, int second) {
		return first + second;
	}

	// implementing subtraction
	@Override
	public int subtraction(int first, int second) {
		return first - second;
	}

	// implementing multiplication
	@Override
	public int multiplication(int first, int second) {
		return first * second;
	}

	// implementing division
	@Override
	public int dividion(int first, int second) {
		// checking if number 2 is zero
		if (second != 0) {
			return first / second;
		} else {
			System.out.println("Cannot Divide by Zero");
			return -1;
		}
	}

}
