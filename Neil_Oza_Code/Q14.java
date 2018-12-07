package hw.weekone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Q14 {
	public static void switchCase() {
		Scanner keyboard= new Scanner(System.in);
		System.out.println("welcome to the Switch function");
		System.out.println("Type 1 to take the square root of a number");
		System.out.println("Type 2 for today's date");
		System.out.println("Type 3 to split a string into an array");
		int switchCase=keyboard.nextInt();
		switch(switchCase) {
		case 1:
			System.out.println("please enter a number");
			int a=keyboard.nextInt();
			System.out.println(Math.sqrt(a));
			break;
		case 2:
			System.out.println(LocalDate.now());
			break;
		case 3:
			String parsingString="I am learning core java";
			String[] splitString=parsingString.split(" ");
			break;
		default:
			System.out.println("invalid input. Type better next time");
		}
		

	}
	
}
