package com.questions.interfaceops;
//Author: Steven Jean-Paul
//Q15 - Operations
public class InterfaceOpsApp {
//This application uses an interfaceoperations object and executes all of its calculation methods.

	public static void main(String[] args) {
		
		InterfaceOpsClass testClass = new InterfaceOpsClass(); //Instantiate class itself
		int num1 = 88;
		int num2 = 15;
		
		System.out.println(testClass.addition(num1, num2));
		System.out.println(testClass.division(num1, num2));
		System.out.println(testClass.multiplication(num1, num2));
		System.out.println(testClass.division(num1, num2));

	}

}
