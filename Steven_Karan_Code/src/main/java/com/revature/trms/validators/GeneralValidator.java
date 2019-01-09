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
	
	
	public static boolean isNumeric(String s) {
		return s.matches("[0-9]+");
	}
	
	public static boolean isDecimalNumber(String s) {
		return s.matches("[0-9]*[.]?[0-9]*");
	}
	
	
	public static boolean isSanitized(String s) {
		return !(s.contains("<") || s.contains(">"));
	}
	
	
}
