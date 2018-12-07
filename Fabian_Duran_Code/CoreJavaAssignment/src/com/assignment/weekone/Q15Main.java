package com.assignment.weekone;

public class Q15Main extends Q15Test {

	public static void main(String[] args) {
		Q15Test maths = new Q15Test();
		double operandA = 7;//hard coded operands
		double operandB = 14;
		System.out.println("Operand A is: "+operandA+"\tOperand B is: "+operandB);
		System.out.println("Here is the sum of A and B: "+maths.addition(operandA, operandB));//performing addition
		System.out.println("Here is the difference of A and B: "+maths.substraction(operandA, operandB));//performing subtraction
		System.out.println("Here is the product of A and B: "+ maths.multiplication(operandA, operandB));//performing multiplication
		System.out.println("Here is the quotient of A and B: " + maths.division(operandA, operandB));//performing division
		
	}

}
