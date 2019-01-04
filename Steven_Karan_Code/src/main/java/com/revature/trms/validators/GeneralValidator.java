package com.revature.trms.validators;

/** 
 * Basic input validation class used to determine if given strings match common patterns.
 * @author karan
 *
 */
public class GeneralValidator {
	
	public static boolean isAlphaNumeric(String s) {
		boolean valid = false;
		String pattern = "[a-zA-Z0-9_]+";
		valid = s.matches(pattern);
		return valid;
	}
	
	public static boolean isPlainText(String s) {
		return s.matches("[a-zA-Z0-9_!@#$%^&*(),./;'\"]+");
	}
	
	
	public static void main(String args[]) {
		System.out.println(isAlphaNumeric("Hello!"));
		System.out.println(isPlainText("Hello!/!@#$%^&*()_\""));
	}
	
}
