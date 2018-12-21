package com.revature.util;

import com.revature.controllers.UserManager;

public class Launcher {
	public static void main(String[] args) {
		try {
			new UserManager().mainMenu();
		} catch (Throwable ex) {
			System.err.println("Uncaught exception - " + ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}
}