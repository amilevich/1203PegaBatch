package com.test.asmt01;

public class Q18Main {

	public static void main(String[] args) {
		
		Q18SubClass subClass = new Q18SubClass();
		
		String a = "Abbbbbbbbbbbb";
		if ( subClass.containsAnUpperCaseChar(a) ) {
			System.out.println(a + " contains an UpperCase character");
		}
		
		String b = "AbcdEfGHi";
		subClass.convertToUppercase(b);
		System.out.println(b + " has been converted to " + subClass.convertToUppercase(b));
		
		String c = "1024";
		subClass.printIntFromString(c);
		

	}

}
