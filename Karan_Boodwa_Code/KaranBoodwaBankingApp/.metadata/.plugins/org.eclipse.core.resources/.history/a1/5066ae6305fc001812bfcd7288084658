package partone;

import java.util.Scanner;

/**
 * Input is a utility wrapper singleton class for Scanner
 * 
 * @author Karan
 *
 */
public class Input {
	private static Input in = new Input();
	
	private static Scanner sc = new Scanner(System.in);

	private Input() {
		// Private Constructor for singleton class
	}

	/**
	 * getInput() returns the singleton instance of this class
	 * 
	 * @return
	 */
	public static Input getInputSingleton() {
		return in;
	}

	// Wrapper methods for common Scanner methods

	/**
	 * getInt() gets an integer from the user entered through standard input
	 * 
	 * @return integer entered from standard input
	 */
	public int getInt() {

		// variable to store user input
		int i = 0;

		// flag used to check if user has entered in valid input
		boolean validInput = false;

		// Loops as long as user has not entered something valid
		while (!validInput) {
			// Checks to see if an int has been entered into the console
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				validInput = true;
			} else {
				// If an int hasn't been entered, error is displayed and what they typed in is
				// removed from the standard input before attempting to read again
				System.out.println("Error: please enter a number");
				sc.next();
			}
		}

		return i;
	}

	/**
	 * getDouble() gets an double from the user entered through standard input
	 * 
	 * @return double that was entered
	 */
	public double getDouble() {

		// variable to store user input
		double d = 0;

		// flag used to check if user has entered in valid input
		boolean validInput = false;

		// Loops as long as user has not entered something valid
		while (!validInput) {
			// Checks to see if an double has been entered into the console
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				validInput = true;
			} else {
				// If a double hasn't been entered, error is displayed and what they typed in is
				// removed from the standard input before attempting to read again
				System.out.println("Error: please enter a decimal number");
				sc.next();
			}
		}
		return d;
	}

	/**
	 * get() gets a string from the user entered through standard input
	 * 
	 * @return string that was entered
	 */
	public String get() {

		// variable to store user input
		String s = "";

		// flag used to check if user has entered in valid input
		boolean validInput = false;

		// Loops as long as user has not entered something valid
		while (!validInput) {
			if (sc.hasNext()) {
				s = sc.next();
				validInput = true;
			} else {
				// If a valid string hasn't been entered, error is displayed and what they typed
				// in is
				// removed from the standard input before attempting to read again
				System.out.println("Error: invalid input");
				sc.next();
			}
		}
		return s;

	}

	public static void main(String[] args) {
		// TDD testing simple i/o cases
		Input userInput = getInputSingleton();

		System.out.println("Enter a number:");
		int i = userInput.getInt();

		System.out.println("Enter a decimal number:");
		double d = userInput.getDouble();

		System.out.println("Enter an expression:");
		String s = userInput.get();

		System.out.println("Number: " + i);
		System.out.println("Decimal: " + d);
		System.out.println("Expression: " + s);

	}

}