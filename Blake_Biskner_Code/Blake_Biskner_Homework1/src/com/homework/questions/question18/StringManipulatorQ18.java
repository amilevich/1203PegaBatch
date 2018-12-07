package com.homework.questions.question18;

/**
 * Abstract Class for String Manipulation
 * @author Blake Biskner
 * @version 1.18
 */

public abstract class StringManipulatorQ18{
	// Default Class State
	private String str;
	// Default Constructor
	public StringManipulatorQ18(String str) {
		this.str=str;
	}
	// Default Accessor
	protected String getStr() {
		return str;
	}
	// Abstract Methods
	/**
	 * Method to check if String contains any uppercase characters
	 * @return is a boolean which is true if there is an uppercase character and false otherwise
	 */
	public abstract boolean isUppercase();
	
	/**
	 * Method to convert String to lowercase
	 * @return is the output String which has converted the input String to all lowercase characters
	 */
	public abstract String toLowercase();
	
	/**
	 * Method to convert String to int
	 */
	public abstract void addToInt();
}