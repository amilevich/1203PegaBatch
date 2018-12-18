package com.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadMyFile {
	public static void main(String[] args) {
		File file = new File("username.txt");

		try {// Look at (1)

			Scanner input = new Scanner(file);

			while (input.hasNextLine()) {
				String[] info = (input.nextLine()).split("\\s+"); // Look at (2)
				String first = info[0];
				String last = info[1];
				String number = info[2];
String username = first.charAt(0) + last + number;
				System.out.println(username.toLowerCase());
				
			}
			input.close(); // Look at (3)
		} catch (IOException e) {
			System.out.println("File doesn't Exist/Not in Directory.");

		}
		
	}
}

