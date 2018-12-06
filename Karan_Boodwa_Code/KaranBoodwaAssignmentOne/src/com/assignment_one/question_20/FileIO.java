package com.assignment_one.question_20;

import java.io.File;
import java.util.Scanner;

/*
 * Q20. Create a notepad file called Data.txt and enter the following:
 * Mickey:Mouse:35:Arizona
 * Hulk:Hogan:50:Virginia
 * Roger:Rabbit:22:California
 * Wonder:Woman:18:Montana
 * 
 * Write a program that would read from the file and print it out to the screen in the following format:
 * Name: Mickey Mouse
 * Age: 35 years
 * State: Arizona State
 */
public class FileIO {

	public static void main(String[] args) {
		// First try to open the file "Data.txt" using a try-with-resources
		File file = new File("Data.txt");
		try (Scanner s = new Scanner(file)) {
			while (s.hasNextLine()) {
				String line[] = ((s.nextLine()).split(":"));

				// If there aren't enough tokens in the line read, the line is skipped
				// Avoids any index out of bounds errors
				if (line.length < 4) {
					continue;
				}
				System.out.println("Name: " + line[0] + " " + line[1]);
				System.out.println("Age: " + line[2] + " years");
				System.out.println("State: " + line[3] + " State");
			}
		} catch (Exception e) {
			System.err.println("Could not Open Data.txt");
		}
	}

}
