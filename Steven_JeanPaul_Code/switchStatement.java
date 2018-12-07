package com.questions.comparator;
import java.time.LocalDate;
//Author - Steven Jean-Paul
//Q14 - Switch Case

public class switchStatement {
	public static void main(String[] args) {

		for (int i = 1; i < 4; i++) { //This for loop is setup to iterate through all three switch casses.
		switch (i) { //The i initializer in the for loop above is used to iterate through the case statement 3 times.

		case 1:
			System.out.print("Case " + i + " presents random number: ");
			System.out.println(Math.sqrt(i+143) + "!"); //Demonstrtes math.random method.
			break;

		case 2:
			System.out.print("Case " + i + " informs that today's date is: ");
			System.out.println(LocalDate.now()); //Prints today's date.
			break;

		case 3:
			System.out.println("Case " + i);
			String parsingString = "I am a Java sensei";
			String[] splitString = parsingString.split(" "); //Parse the parsingString string
			for (int thru = 0; thru < splitString.length ;thru++) {
				System.out.println("And here's your string!: " + splitString[thru]); //Print each string object on a new line.
			}
			break;

		default:
			System.out.println("No default case."); //No default case is set.

		}
		}
	}

}
