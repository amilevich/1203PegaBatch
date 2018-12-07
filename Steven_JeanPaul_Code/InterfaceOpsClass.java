package com.questions.interfaceops;
//Author: Steven Jean-Paul
//Q15 - Operations
public class InterfaceOpsClass implements OperationInterface{ //Implements OperationInterface
	private int addend = 0;									  //Instantiates an object from interface methods
	private int dividend = 0;
	private int product = 0;
	private int difference = 0;
	
	public int addition(int a, int a2) {
		addend = a + a2;
		return addend;
	}
	
	public int subtraction(int s, int s2) {
		difference = s - s2;
		return difference;
	}
	
	public int division(int d, int d2) {
		dividend = d / d2;
		return dividend;
	}
	
	public int multiplication(int m, int m2) {
		product = m * m2;
		return product;
	}
}
