package com.testing.playground;

import java.util.Scanner;

public class Room {
	private static Scanner input = new Scanner(System.in);
enum userTypes {CUSTOMER, EMPLOYEE, ADMIN};
	public static void main(String[] args) {
		System.out.println();
		String type = "CUSTOMER";
		userTypes ut = userTypes.valueOf(type);
		//userTypes.values("1");
//		String userType = "CUSTOMER";
//		for (userTypes ut : userTypes.values())		
		System.out.println(ut);
		
	}

}
