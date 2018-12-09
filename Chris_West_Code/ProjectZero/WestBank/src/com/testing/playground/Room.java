package com.testing.playground;

import java.util.Random;
import java.util.Scanner;

public class Room {
	private static Scanner input = new Scanner(System.in);
	private static String errorMessage = "Input is wrong data type!";

enum userTypes {CUSTOMER, EMPLOYEE, ADMIN};
	public static void main(String[] args) {
		System.out.println();
		String type = "CUSTOMER";
		userTypes ut = userTypes.valueOf(type);
		
		
//		Random rm = new Random();
//		System.out.println(rm.nextInt(9000)+1000); // 
		//userTypes.values("1");
//		String userType = "CUSTOMER";
//		for (userTypes ut : userTypes.values())		
//		System.out.println(ut);
//		String x = input.next();
//		String y = input.nextLine();
//		System.out.println(x);
//		System.out.println(y);
		
//		input = isInt(input);
//		int x = input.nextInt();
//		System.out.println(x);
		
//		String s = input.next();
//		System.out.println(isALetter(s));
//		input = isInt(input);
//		int x = input.nextInt();
//		System.out.println(x);
//	}
//	public static Scanner isInt(Scanner input) {
//		while(!input.hasNextInt()) {
//			System.out.println(errorMessage);
//			input = new Scanner(System.in);
//			}
//			return input;
//		
//	}
//	
//	public static String isALetter(String s) {
//		while (!s.matches("[a-zA-Z]")) {
//			System.out.println(errorMessage);
//			input = new Scanner(System.in);
//			s = input.next();
//		}
//
//		return s;
	}
}
