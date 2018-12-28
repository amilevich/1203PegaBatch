package com.revature.util;

import java.util.Scanner;

public class Validation {
	static Scanner scanner = new Scanner(System.in);
	
	public static String getStringInput() {
		String string = scanner.nextLine();
		return string;
	}
	
	public static int getIntInput() {
		Integer value = null;
		while(value == null) {
			if(scanner.hasNextInt())
				value = scanner.nextInt();
			else {
				scanner.nextLine();
			}
		} 
		return value;
	}
	
	public static double getDoubleInput() {
		Double value = null;
		while(value == null) {
			if(scanner.hasNextDouble())
				value = scanner.nextDouble();
			else {
				scanner.nextLine();
			}
		} 
		return value;
	}
	
	public static void getPause() {
		scanner.nextLine();
	}
	
}
