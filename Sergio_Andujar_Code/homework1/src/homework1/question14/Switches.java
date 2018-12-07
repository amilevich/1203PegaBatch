package homework1.question14;
import java.lang.Math;
import java.util.Date;

/**
 * switch case practice
 * @author Sergio Andujar
 * @version 1.8
 */

public class Switches {

	public static void main(String[] args) {
		
			useSwitchCase(1);
			useSwitchCase(2);
			useSwitchCase(3);
		
		

	}

	private static void useSwitchCase(int input) {
		switch(input) {
		// case 1: display the sqrt of the number
		case 1:
			double number = 4;
			double sqrtOfNumber = Math.sqrt(number);
			System.out.println("The sqrt of " + number + " is " + sqrtOfNumber);
			System.out.println();
			break;
		// case 2: display today's date
		case 2:
			Date date = new Date();
			System.out.print("Here is today's date: ");
			System.out.println(date);
			System.out.println();
			break;
		// case 3: displayed words split on whitespace
		case 3:
			String sentence = new String("I am learning Core Java");
			String[] splitSentence = sentence.split(" ");
			System.out.println("Here are the split words: ");
			for(String word : splitSentence) {
				System.out.println(word);
			}
			System.out.println();
			break;
			
		}
		
		
	}

}
