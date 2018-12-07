package com.homework1;

public class Question6 {
	
	public static boolean isEven(int x) {
		
		// integer division by 2 will truncate odd numbers but exactly divide even ones.
		// therefore, if the number is odd, dividing and multiplying by two will not be equal
		// to an unmodified version.
		if ((x/2) * 2 == x)
			return true;
		else
			return false;
		
	}
	
	public static void main(String[] args) {
		int toTest = 200;
		if (isEven(toTest))
			System.out.println("Even");
		else
			System.out.println("Odd");
	}
}
