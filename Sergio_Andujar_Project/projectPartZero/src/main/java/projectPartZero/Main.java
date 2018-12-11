package projectPartZero;

import java.util.Scanner;


public class Main {
	
	// bad practice
	// need to put into Singelton later
	// only want it to be used here in this package
	protected static final Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		Menu.aMenu();
	}

}
