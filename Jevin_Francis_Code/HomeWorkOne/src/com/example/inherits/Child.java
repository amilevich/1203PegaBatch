package com.example.inherits;

public class Child extends Parent {

	// implementing the method in child class to check for uppercase
	@Override
	public Boolean checkForUpper(String str) {
		if (str.length() != 0) {
			String allLower = str.toLowerCase();
			if (allLower == str) {
				return false;
			} else {
				return true;
			}
		} else {
			System.out.println("Empty String!");
			return null;
		}

	}

	//implementing the method t0 convert string to uppercase
	@Override
	public String convertToUpper(String str) {
		if (str.length() != 0) {
			return str.toUpperCase();
		} else {
			System.out.println("Empty String!");
			return null;
		}

	}
	
	// implementing the method to add 10 to the string
	@Override
	public int convertAddTen(String str) {
		return Integer.parseInt(str) + 10;
	}
}
