package com.project.files.menus;

public class PersonalMenu {
	// Class State
	private String menuName;
	private String menuMessage;
	private String menuPrompt = "Please Select an Option by Pressing the Indicated Key";
	private String menuOptions[];
	

	// Class Behavior
	public PersonalMenu() {
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

	public void displayHeader() {
		System.out.println(menuName);
		for(int i=1;i<=menuName.length();i++) {
			System.out.print("=");
		}
		System.out.print('\n');
		System.out.println(menuMessage);
		System.out.println(menuPrompt);
	}

	public void displayOptions() {
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("[" + (i + 1) + "] " + this.menuOptions[i]);
		}
	}
}
