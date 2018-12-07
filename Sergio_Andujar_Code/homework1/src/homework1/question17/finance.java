package homework1.question17;

import java.util.Scanner;

/**
 * interest calculation
 * @author Sergio Andujar
 * @version 1.8
 */

public class finance {
	
	public static void main(String[] arg) {
		
		String principal = "";
		String ROI = "";
		String years = "";
		
		// Instantiate scanner object to get input
		Scanner reader = new Scanner(System.in);
		
		System.out.print("Enter principal: ");
		principal = reader.next();
		System.out.println();
		System.out.print("Enter Return on Investment: ");
		ROI = reader.next();
		System.out.println();
		System.out.print("Enter number of years: ");
		years = reader.next();
		System.out.println();
		
		// convert the values into float
		float principals = new Float(principal);
		float ROIs = new Float(ROI);
		float year = new Float(years);
		
		//calculate the interest
		float interest = principals * ROIs * year;
		
		System.out.println("Your interest is: " + interest);
		
		// close the scanner
		reader.close();
		
		
	}

}
