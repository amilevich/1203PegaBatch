package com.homework.questions.question15;

/**
 * Driver Class for Operations Class
 * @author Blake Biskner
 * @version 1.15
 */

public class MainQ15 {
	
	public static void main(String[] args) {
		// Variable Declaration Operand Initialization and Class Instantiation
		double addNum, subNum, multNum, divNum;
		double operand1=10.0;
		double operand2=2.0;
		
		OperationsQ15 op=new OperationsQ15(operand1);
		// Class Implementation
		addNum=op.addition(operand2);
		System.out.println(operand1+" + "+operand2+" = "+addNum);
		subNum=op.subtraction(operand2);
		System.out.println(operand1+" - "+operand2+" = "+subNum);
		multNum=op.multiplication(operand2);
		System.out.println(operand1+" * "+operand2+ " = "+multNum);
		divNum=op.division(operand2);
		System.out.println(operand1+ " / "+operand2+" = "+divNum);
	}
}