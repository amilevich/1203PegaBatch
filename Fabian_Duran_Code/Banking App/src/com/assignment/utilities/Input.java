package com.assignment.utilities;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class will be a singleton, originally this was Karan's idea
 * so this code will be inspired by his explanation
 * it will also function to stop possible exceptions from happening 
 * based on data type mismatch on the scanner's .next___ method
 * @author Fabian

*/
public class Input {//will be used to enter inputs and validate a correct input
	private static Scanner scan = new Scanner(System.in);//will be used to scan things in
	private static Input input = new Input();//the singleton
	private static DecimalFormat secChange = new DecimalFormat(".##");//secures that the doubles will only hold up to two values after decimal point
	
	private Input() {};//private constructor
	
	public static Input getInput() {//creates singleton
		return input;
	}
	
	
	//inputInt will be used for some menu selection and to select Account Ids (they will be ints)
	public int inputInt () {//will be used to make sure input is fact an integer, which is what we want
		int temp = 0;//temporary variable that will return value if input is an integer
		boolean valid = false;//will be used to break out of while loop
		
		while (!valid) {
			if (scan.hasNextInt()) {//if the input can be read as an int: go ahead
				temp =  scan.nextInt();//store input into temp variable
				valid = !valid;//to switch states and breakout
			}
			else {//if it was anything other than an int
				System.out.print("Incorrect input. Please enter a valid number: ");
				scan.next();//ask for new input
				System.out.println();//new line
			}
		}
		return temp;
		
	}
	/*
	 * inputDouble will be used to make sure input is fact a double, which is what we want
	 * point of interest is that this will only be used to get money amounts from the user
	 * so I plan taking whatever double they give and cutting whatever value is in the 
	 * thousandth value and there on (third digit right of the decimal point)
	 * a note is made in the menu screen that this will occur
	*/
	public double inputDouble () {
		double temp = 0;
		boolean valid = false;
		
		while (!valid) {
			if(scan.hasNextDouble()) {
				temp = scan.nextDouble();
				temp = Double.parseDouble(secChange.format(temp));//cuts off after two values
				valid = !valid;
			}
			else {//if it was anything other than an double
				System.out.print("Incorrect input. Please enter a valid amount: ");
				scan.next();
				System.out.println("Note: the system will only read the first two values after the decimal point!");
			}
		}
		return temp;
	}
	//now for strings
	public String inputString() {
		String temp = "";
		boolean valid = false;
		while (!valid) {
			if(scan.hasNext()) {
				temp = scan.next();
				valid = !valid;
			}
			else {//if it was anything other than an string
				System.out.print("Incorrect input. Please enter a valid input: ");//this will be used to identify username and account types
				scan.next();
				System.out.println();//new line
			}
		}
		return temp;
	}
	

}
