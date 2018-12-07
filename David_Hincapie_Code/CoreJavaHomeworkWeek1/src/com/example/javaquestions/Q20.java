package com.example.javaquestions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.StringTokenizer;

public class Q20 {
	/*
	 * Q20. Create a notepad file called Data.txt and enter the following:
	 * Mickey:Mouse:35:Arizona Hulk:Hogan:50:Virginia Roger:Rabbit:22:California
	 * Wonder:Woman:18:Montana
	 * 
	 * Write a program that would read from the file and print it out to the screen
	 * in the following format:
	 * 
	 * Name: Mickey Mouse Age: 35 years State: Arizona State
	 */
	public static void main(String[] args) {
		String line;

		// StringTokenizer class in Java is used to break a string into tokens.
		StringTokenizer stringTokenizer = null;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Data.txt"))) {
			while ((line = bufferedReader.readLine()) != null) {

				stringTokenizer = new StringTokenizer(line, ":");
				String[] data = { "Name", "Age", "State" };

				int counter = 0;

				System.out.println(
						data[counter] + ": " + stringTokenizer.nextToken() + " " + stringTokenizer.nextToken());

				while (stringTokenizer.hasMoreTokens()) {
					counter++;
					System.out.println(data[counter] + ": " + stringTokenizer.nextToken());
				}

				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}