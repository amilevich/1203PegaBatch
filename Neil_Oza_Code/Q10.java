package hw.weekone;

import java.util.Scanner;

public class Q10 {
	public static void minTwoNumbers() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("type the first number");
		int num1=keyboard.nextInt();
		System.out.println("type the second number");
		int num2= keyboard.nextInt();
		int minimum =(num1>num2) ? num2: num1;
		System.out.println(minimum);
	}

}
