package com.assignment.weekone;

public class Q10 {

	public static void main(String[] args) {
		int a = 10;//some values
		int b = 7;
		int c = 4;
		int d = 8;
		int minValue = 0;
		minValue = a<b ? a:b;//use of ternary operators
		System.out.println("The minimum of "+a+ " and "+b+" is: "+minValue);
		minValue = c<d? c:d;//use of ternary operators
		System.out.println("The minimum of "+c+ " and "+d+" is: "+minValue);
	}

}
