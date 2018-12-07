package com.homework.q15;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double x = 55;
		double y = 23;
		double result = 0;
		Operations obj = new Arithmetics();
		
		result = obj.add(x, y);
		System.out.println(x + " + " + y + " = " + result);
		result = obj.subtract(x, y);
		System.out.println(x + " - " + y + " = " + result);
		result = obj.multiply(x, y);
		System.out.println(x + " * " + y + " = " + result);
		result = obj.divide(x, y);
		System.out.println(x + " / " + y + " = " + String.format("%.2f", result));

	}

}
