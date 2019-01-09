package com.revature.trms.validators;


public class PasswordValidator {
	
	
	public static boolean validatePassword(String password) {
		if (password.contains(" ") || password.length() > 25 || password.length() < 7) {
			return false;
		}
		
		return true;
	}
	
	
}
