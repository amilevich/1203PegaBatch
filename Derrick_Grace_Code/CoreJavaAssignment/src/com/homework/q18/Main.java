package com.homework.q18;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StrManip obj = new StrChild();
		String str;
		boolean bool = false; 
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a string: ");
		str = scan.next();
		scan.close();
		
		bool = obj.checkUpperCase(str);
		
		System.out.println("String had uppercase letter/s: " + bool);
		
		str = obj.lowerToUpper(str);
		System.out.println("String with all uppercase letters: " + str);
		
		obj.stringToInt(str);
		

	}

}
