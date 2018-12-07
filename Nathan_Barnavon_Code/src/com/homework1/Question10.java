package com.homework1;

public class Question10 {
	
	public static int findMin(int num1, int num2) {
		// ternary operator will return num2 if evaluated to true; num1 if false
		return (num1 > num2) ? num2 : num1;
	}
	
	public static void main(String[] args) {
		int num1 = 17;
		int num2 = 2;
		
		System.out.println(findMin(num1, num2));
	}

}
