package com.project.files;

public class Menu {
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

	// Class Methods
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

	public void displayHeadPrompt() {
		this.displayHeader();
		this.displayPrompt();
	}

	public void displayOptions() {
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("[" + (i + 1) + "] " + this.menuOptions[i]);
		}
	}
}
