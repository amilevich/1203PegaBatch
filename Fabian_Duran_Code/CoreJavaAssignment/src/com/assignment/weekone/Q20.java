package com.assignment.weekone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q20 {

	public static void main(String[] args) {
		File file = new File("Q20.txt");//got this from Matt to help read in file
		
		try (Scanner reader = new Scanner (file);){//read in txt file into scanner
			
			while(reader.hasNextLine()) {//while there is a next line to take keep reading txt file
				String lineRead = reader.nextLine();//gets the string from txt
				String [] readerLine = lineRead.split(":");//separate line into string by the :
				System.out.println("Name: "+ readerLine[0] + readerLine [1]);//only so many indices from line read
				System.out.println("Age: "+ readerLine[2] + " years");
				System.out.println("State: "+ readerLine[3]+" State\n");
			}
			reader.close();//close scanner
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

}
