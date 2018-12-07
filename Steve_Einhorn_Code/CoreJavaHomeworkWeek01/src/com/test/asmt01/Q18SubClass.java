package com.test.asmt01;

public class Q18SubClass extends Q18AbstractClass {
	
	public boolean containsAnUpperCaseChar (String text) {
		
		boolean containsUppercase = false;
		
		for (int i = 0; i < text.length(); i++) {
			if ( Character.isUpperCase(text.charAt(i)) ) {
				containsUppercase = true;
				break;
			}
		}
		
		return containsUppercase;
		
	}
	
	public String convertToUppercase (String text) {
		
		StringBuffer stringBuffer = new StringBuffer(text);
		
		for (int i = 0; i < stringBuffer.length(); i++) {
			if ( Character.isLowerCase(stringBuffer.charAt(i)) ) {
				stringBuffer.setCharAt(i, Character.toUpperCase(stringBuffer.charAt(i)));
			}
		}
		
		return stringBuffer.toString();
		
	}
	
	public void printIntFromString (String text) {
		
		int number = 0;
		
		if ( text.contains("[a-zA-Z]+") == false ) {
			number = Integer.parseInt(text);
		}
		number = number + 10;
		
		System.out.println("Your number plus 10 is: " + number);
		
	}

}
