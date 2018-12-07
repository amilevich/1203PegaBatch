package com.test.asmt01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Q14 {

	public static void main(String[] args) {
		
		int function = 1;
        int number = 9;
        String toSplit = "I am learning Core Java";
                
        switch (function) {
        
            case 1:  printSquareRoot(number);
                     break;
                     
            case 2:  printTodaysDate();
                     break;
                     
            case 3:  printSplitString(toSplit);
            		 break;
                     
            default: System.out.println("Invalid function selected!");
                     break;
        }
        
	}
	
	private static void printSquareRoot(int number) {
		System.out.println("The square root of " + number + " is " + Math.sqrt(number));
	}
	
	private static void printTodaysDate() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		
		System.out.println("Today's Date is " + dateFormat.format(date));
		
	}
	
	private static void printSplitString(String toSplit) {
		
		String [] stringArray = toSplit.split(" ");
		
		 for (String text : stringArray) {
			 System.out.println(text);
		 }
		 
	}

}
