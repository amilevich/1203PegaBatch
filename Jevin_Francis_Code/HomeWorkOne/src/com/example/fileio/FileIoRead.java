//Create a notepad file called Data.txt and enter the following:
//Mickey:Mouse:35:Arizona
//Hulk:Hogan:50:Virginia
//Roger:Rabbit:22:California
//Wonder:Woman:18:Montana
// 
//Write a program that would read from the file and print it out to the screen in the following format:
// 
//Name: Mickey Mouse
//Age: 35 years
//State: Arizona State

package com.example.fileio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileIoRead {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fstream = new FileInputStream("Data.txt");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		ArrayList<String> lines = new ArrayList<>();
		//Read File Line By Line
		while ((strLine = buffer.readLine()) != null)   {
		//Adding the line to an ArrayList
		  lines.add(strLine);
		}
		
		
		for(String str:lines) {
			String[] splitLine = new String[4];
			splitLine = str.split(":");
			
			//Printing the formated List
			System.out.println("Name: "+ splitLine[0]+ " "+ splitLine[1]);
			System.out.println("Age: "+ splitLine[2]+" Years");
			System.out.println("State: "+ splitLine[3]+ " State\n");
		}
		
		//Closing the input stream
		buffer.close();
	}
}
