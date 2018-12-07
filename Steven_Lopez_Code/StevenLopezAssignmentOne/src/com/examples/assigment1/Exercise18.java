package com.examples.assigment1;

import com.examples.assigment1.Exercise18;

public class Exercise18 {
	// Q18. Write a program having a concrete ;subclass that inherits three abstract
	// methods from a superclass. Provide the following three implementations in the
	// subclass corresponding to the abstract methods in the superclass:
	//
	// 1. Check for uppercase characters in a string, and return ‘true’ or ‘false’
	// depending if any are found.
	// 2. Convert all of the lower case characters to uppercase in the input string,
	// and return the result.
	// 3. Convert the input string to integer and add 10, output the result to the
	// console.
	// Create an appropriate class having a main method to test the above setup.

	public static void main(String[] args) {
		Exercise18 e = new Exercise18();
		StringPlaySimple sp = e.new StringPlaySimple();// Referencing outer class to initiate inner class
														// StringPlaySimple.
		//Results of SprintPlaySimple methods
		System.out.println("\"HelloWorld\" has uppercase letter: " + sp.CheckUpperCase("HelloWorld"));
		System.out.println("\"Tampa, Florida\" in lowercase: " + sp.toLowerCase("Tampa, Florida"));
		System.out.println("\"The string 2018 converted to and integer -> " + sp.stringToInteger("2018"));
		System.out.println(1 + sp.stringToInteger("2018") + " <- Verification");

	}

	public abstract class StringPlay { //Abstract class functioning more like an interface
		public abstract boolean CheckUpperCase(String text);

		public abstract String toLowerCase(String text);

		public abstract int stringToInteger(String number);
	}

	
	public class StringPlaySimple extends StringPlay {

		//cycle through the string passed as a parameter and return true is there's an uppercase others return false.
		public boolean CheckUpperCase(String text) {
			for (char l : text.toCharArray()) {
				if (String.valueOf(l).toUpperCase().equals(l))
					return true;
				else
					return false;
			}
			return false;
		}

		//If the String passed is not null return it as lowercase, if not send an error message.
		@Override
		public String toLowerCase(String text) {

			if (text != null)
				return text.toLowerCase();
			else
				return "Error: Must input a legit String";
		}

		//Try to return string as a integer after parsing it, if unsuccessful print error message and return -0;
		@Override
		public int stringToInteger(String number) {
			int result = 0;
			try {
				result = Integer.parseInt(number);

			} catch (NumberFormatException ex) {
				System.out.println("Error: The input string must be convertable to an integer");
				return -0;
			}
			return result;
		}
	}
}
