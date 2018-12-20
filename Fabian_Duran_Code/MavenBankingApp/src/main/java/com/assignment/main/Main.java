package com.assignment.main;

import com.assignment.utilities.Menus;

public class Main {
	
	/*
	 * This is it. Just two lines of code that separates us from insanity.
	 * The truest bug lies within.
	 * e pluribus unum, Temet Nosce 
	 */	
	public static void main(String[] args) {
		Menus menu = Menus.getMenu();
		menu.initialPrompt();
	}

}
