package com.revature.trms.validators;


public class PasswordValidator {
	
	
	public static boolean validatePassword(String password) {
		if (password.contains(" ") || password.length() > 20 || password.length() < 7) {
			System.out.println("Invalid password");
			return false;
		}
		
		return true;
	}
	
	
}
