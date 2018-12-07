package com.homework1;

import java.io.*;

public class Question20 {

	public static String formatLine(String str) {
		String toReturn = "";
		String[] parts = str.split(":");
		if (parts.length != 4) {
			return "Misformed Data";
		}
		
		toReturn = "Name: " + parts[0] + " " + parts[1] + "\n"
				  + "Age: " + parts[2] + " years\n"
				  + "State: " + parts[3] + " State\n";
		return toReturn;
	}
	
	public static void main(String[] args) {

		File f = new File("Data.txt");
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
			String toFormat = "";
			while((toFormat = bf.readLine()) != null) {
				System.out.println(formatLine(toFormat));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Our file doesnt exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something messed up with input");
			e.printStackTrace();
		}
		
	}
	
}
