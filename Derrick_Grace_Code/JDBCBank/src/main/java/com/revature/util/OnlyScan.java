package com.revature.util;

import java.util.Scanner;

public class OnlyScan {
	static Scanner scan = new Scanner(System.in);
	
	private OnlyScan() {
		super();
	}
	
	public static Scanner getScanner() {
		return scan;
	}
}
