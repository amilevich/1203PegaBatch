package com.assignment.main;

import com.assignment.utilities.Menus;

public class Main {
	
	/*eventually main will also have method to read in text files and 
	 * populate Person hashmap, account hashmap, and each customer array list
	 * but for now...not so much
	 */	
	public static void main(String[] args) {
		Menus menu = Menus.getMenu();
		menu.initialPrompt();
	}

}
