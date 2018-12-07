package com.example.javaquestions;
import java.util.Scanner;

public class Q10 {
	// Q10. Find the minimum of two numbers using ternary operators.
	public static void main(String[] args) {
		int num1 = 2;
		int num2 = 5;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter an integer: ");
		num1 = scanner.nextInt();
		System.out.println("Enter an integer: ");
		num2 = scanner.nextInt();
		System.out.print("The minimum of the two number is :");
		System.out.println(num1 < num2 ? num1 : num2);
		scanner.close();
	}

}
