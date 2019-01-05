package com.homework.q15;

public class Arithmetics implements Operations{

	@Override
	public double add(double x, double y)
	{
		double result = x + y;
		return result;
	}

	@Override
	public double subtract(double x, double y) {
		
		double result = x - y;
		return result;
	}

	@Override
	public double multiply(double x, double y) {
		double result = x * y;
		return result;
	}

	@Override
	public double divide(double x, double y) {
		
		double result = x / y;
		return result;
	}
}
