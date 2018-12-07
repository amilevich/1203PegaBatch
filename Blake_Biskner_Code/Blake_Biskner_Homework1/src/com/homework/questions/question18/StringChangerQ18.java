package com.homework.questions.question18;

/**
 * Class for String Methods
 * @author Blake Biskner
 * @version 1.18
 */

public class StringChangerQ18 extends StringManipulatorQ18{
	// Class State Default to Superclass
	
	// Class Constructor
	public StringChangerQ18(String str) {
		super(str); // Invoke super constructor as it is the same for this class
	}
	
	// Abstract Class Implementation
	public boolean isUppercase() {
		Character ch;
		boolean upper=true; // By default there is an uppercase letter
		for(int i=0;i<getStr().length();i++) {
			ch=(getStr().charAt(i)); // Boxing the char of the String at index i to gain access to utility methods
			if(Character.isUpperCase(ch)) {
				return upper; // If there is an uppercase letter exit code and return true
			}
		}
		upper=false; // If there are no uppercase letters
		return upper;
	}
	
	public String toLowercase() {
		return getStr().toLowerCase(); //Employ String toLowerCase method and return function return String
	}
	
	public void addToInt() {
		int strValue=Integer.valueOf(getStr()); // Returns integer value of String
		System.out.println(strValue+10);
	}
}
