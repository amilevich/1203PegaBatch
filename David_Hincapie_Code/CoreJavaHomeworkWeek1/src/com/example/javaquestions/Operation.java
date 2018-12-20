package com.example.javaquestions;

public class Operation implements OperandInterface {

	public Operation() {
		super();
	}

	@Override
	public int addition(int n1, int n2) {
		return n1 + n2;
	}

	@Override
	public int subtraction(int n1, int n2) {
		return n1 - n2;
	}

	@Override
	public int multiplication(int n1, int n2) {
		return n1 * n2;
	}

	@Override
	public int divison(int n1, int n2) {
		return n1 / n2;
	}

}
