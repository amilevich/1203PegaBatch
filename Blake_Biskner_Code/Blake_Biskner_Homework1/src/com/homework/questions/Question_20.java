package com.homework.questions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * File Reader Method
 * @author Blake Biskner
 * @version 1.20
 */

public class Question_20 {
	public static void main(String[] args) {
		/* Employ try with resources and a bufferedreader object to read in file
		 * To explain, try with resources (ie ()) will execute the autoclosable functionality of bufferedreader (thus saving me a finally block to close)
		 * Further, I use bufferedreader as it is faster than reading in the file byte by byte as with filereader
		 * Last, to explain the syntax, bufferedreader is kind of a "wrapper" it just tells filereader how to read, so you still must instantiate filereader
		 */
		try(BufferedReader br=new BufferedReader(new FileReader("src\\com\\homework\\questions\\Data.txt"))){
			// Declare and Initialize Variables
			String lineArray[];
			String name, age, state;
			String line=null;
			while((line=br.readLine())!=null) { // Read to end of file
				lineArray=line.split(":"); // Parse String on colons
				// Set data
				name="Name: "+lineArray[0]+" "+lineArray[1];
				age="Age: "+lineArray[2]+" years";
				state="State: "+lineArray[3]+" State";
				// Display Data
				System.out.println(name);
				System.out.println(age);
				System.out.println(state);
				System.out.print('\n');
			}
		} catch(IOException e) {
			e.printStackTrace(); // Shows where exception came from
		}
	}
}

