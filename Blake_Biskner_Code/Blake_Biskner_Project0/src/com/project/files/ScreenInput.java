package com.project.files;

import java.util.Scanner;

public class ScreenInput {
	// Class State
	private String inputScreenName="Default";
	private String inputScreenPrompt="Please Enter the Following Information";
	
	private String[] inputScreenInput;
	// Class Behavior
	public ScreenInput() {
	}
	
	// Getters and Setters
	public String getName() {
		return inputScreenName;
	}
	public String[] getScreenInput() {
		return inputScreenInput;
	}
	public void setName(String inputScreenName) {
		this.inputScreenName=inputScreenName;
	}
	public void setScreenInput(String[] inputScreenInput) {
		this.inputScreenInput=inputScreenInput;
	}
	
	// Concrete Class Methods
	public void displayHeader() {
		System.out.println(inputScreenName);
		// Print a line of = under each character of the menu name
		for (int i = 1; i <= getName().length(); i++) {
			System.out.print("=");
		}
		System.out.print('\n');
		System.out.println(inputScreenPrompt);
	}
	
//	public void promptInput(Scanner userIn,String[] inputScreenInput) {
//		for(String prompt:inputScreenInput) {
//			System.out.println("Please Input "+prompt);
//			userIn.
//			
//		}
//	}
}
