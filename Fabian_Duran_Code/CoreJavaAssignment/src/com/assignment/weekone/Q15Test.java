package com.assignment.weekone;

public class Q15Test implements Q15MathInterface {//implementing interface
		
	@Override
	public double addition(double a, double b) {//doing the adding
		return a + b;
	}

	@Override
	public double substraction(double a, double b) {//subtracting
		return a - b;
	}

	@Override
	public double multiplication(double a, double b) {//multiplying
		return a * b;
	}

	@Override
	public double division(double a, double b) {//division
		if (b == 0)
			System.out.println("Cannot divide by 0");//compensating for divide by zero exception
		else
			return a / b;
		return 0;
	}
	

}
