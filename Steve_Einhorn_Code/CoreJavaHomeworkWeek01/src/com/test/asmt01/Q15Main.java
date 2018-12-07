package com.test.asmt01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q15Main {
	
	public static void main(String[] args) {
		
		int operand1 = 5;
		int operand2 = 2;
		
		Q15Operations operations = new Q15Operations();
		System.out.println(operations.addition(operand1,operand2));
		System.out.println(operations.subtraction(operand1,operand2));
		System.out.println(operations.multiplication(operand1,operand2));
		System.out.println(operations.division(operand1,operand2));
	    
	}

}
