package com.ex.junit;

public class CalculatorImpl implements Calculator {
	
	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int subtract(int x, int y) {
		return x - y;
	}

	@Override
	public int multiply(int x, int y) {
		return x * y;
	}

	@Override
	public int divide(int x, int y) {
		return x / y;
	}

	@Override
	public void xyzMethod() throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}

	//this method seems to take a while
	@Override
	public void timeMethod() {
		while(true){
			
		}
		
	}

}
