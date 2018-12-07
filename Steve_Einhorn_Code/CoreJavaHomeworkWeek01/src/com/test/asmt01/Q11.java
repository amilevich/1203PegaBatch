package com.test.asmt01;

import com.test.asmt01a.Q11FloatVariables;

public class Q11 {

	public static void main(String[] args) {
		
		float floatVar1 = 3.14159f;
		float floatVar2 = 98.6f;
		
		Q11FloatVariables q11FloatVariables = new Q11FloatVariables();
		q11FloatVariables.setFloatVar1(floatVar1);
		q11FloatVariables.setFloatVar2(floatVar2);
		
		System.out.println( "FloatVar1=" + q11FloatVariables.getFloatVar1() + "\t"
						  + "FloatVar2=" + q11FloatVariables.getFloatVar2() );

	}

}
