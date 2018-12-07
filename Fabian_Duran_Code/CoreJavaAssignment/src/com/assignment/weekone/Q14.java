package com.assignment.weekone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Q14 {
	public static void main(String[] args) {
		int numsq =0;//to be used for square roots
		Scanner scanner = new Scanner(System.in);//opens scanner class
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//to be used for displaying later on -> found through google search
		Date date = new Date();//new date object
		String soonToSplit = "I am learning Core Java";//string to be split
		System.out.print("Choose 1, 2, or 3: ");//prompts user to do specific case
		int switchcase = scanner.nextInt();//lets user decide
		System.out.println();
		switch (switchcase) {//depending on input from user
		case 1:{
			System.out.print("Choose a number to find the square root of: ");
			numsq = scanner.nextInt();//takes input from user
			System.out.println("\n The square root of "+numsq+" is: "+Math.sqrt(numsq));//shows square root
			break;
		}
		case 2:{
			System.out.println("Today's date is: " + dateFormat.format(date));//shows date
			break;
		}
		case 3:{
            String[] splitString = soonToSplit.split("");//does the spliting
            for (int i=0; i<splitString.length; ++i) 
                System.out.print(splitString[i] + " ");//places a space between each string character
            System.out.println();
			break;
		}
		default:
			System.out.println("Not a valid input");//if anything other 1,2, or 3 is inputted
			break;
		}
		scanner.close();//closes scanner
	}
	
}
