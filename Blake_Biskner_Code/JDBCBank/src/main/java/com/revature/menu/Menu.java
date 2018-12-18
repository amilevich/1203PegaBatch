package com.revature.menu;

import java.util.Scanner;

/**
 * Abstract Class for General (Not Personalized Menus)
 *
 * @author Blake Biskner
 * @version 2.0
 */

public abstract class Menu {
	// Class State
	private String menuName = "Default";
	private String menuMessage = "Default";
	private String menuPrompt = "Please Select an Option By Pressing the Indicated Key"; // Has an initial newline
	private String menuOptions[];
	
	// Class Behavior
	// Constructors
	public Menu() {
	}

	public Menu(String menuName) {
		this.menuName = menuName;
	}

	public Menu(String menuName, String menuMessage) {
		this(menuName);
		this.menuMessage = menuMessage;
	}

	public Menu(String menuName, String menuMessage, String[] menuOptions) {
		this(menuName, menuMessage);
		this.menuOptions = menuOptions;
	}

	// Getters and Setters
	public String getName() {
		return menuName;
	}

	public String getMessage() {
		return menuMessage;
	}

	public String[] getOptions() {
		return menuOptions;
	}

	public void setName(String menuName) {
		this.menuName = menuName;
	}

	public void setMessage(String menuMessage) {
		this.menuMessage = menuMessage;
	}

	public void setOptions(String[] menuOptions) {
		this.menuOptions = menuOptions;
	}

	// Concrete Class Methods
	public void displayHeader() {
		System.out.println(menuName);
		// Print a line of = under each character of the menu name
		for (int i = 1; i <= getName().length(); i++) {
			System.out.print("=");
		}
		System.out.print('\n');
		System.out.println(menuMessage);
	}

	public void displayPrompt() {
		System.out.println(menuPrompt);
	}

	// Separated header and prompt so I could display the
	// length of the hash map in the welcome message
	public void displayHeadPrompt() {
		this.displayHeader();
		this.displayPrompt();
	}

	public void displayOptions() {
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("[" + (i + 1) + "] " + this.menuOptions[i]);
		}
	}

	// Abstract Methods
	
	/**
	 * Displays the menu name, message, prompt and options
	 * 
	 * @return the number of options to use for later validation
	 */

	public abstract int menuDisplay();

	/**
	 * Switch statement which allows user to select desired option
	 * 
	 * @param userIn
	 * @param userInput
	 */

	public abstract void menuInput(Scanner userIn, int userInput);

	/**
	 * Invokes display amd input methods and validates user input
	 * 
	 * @param userIn
	 */

	public abstract void menuDriver(Scanner userIn);
}
