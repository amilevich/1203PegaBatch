package com.examples.assigment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise20 {
	// Q20. Create a notepad file called Data.txt and enter the following:
	// Mickey:Mouse:35:Arizona
	// Hulk:Hogan:50:Virginia
	// Roger:Rabbit:22:California
	// Wonder:Woman:18:Montana
	//
	// Write a program that would read from the file and print it out to the screen
	// in the following format:
	//
	// Name: Mickey Mouse
	// Age: 35 years
	// State: Arizona State

	public class Person {	//Person class to handle input file stream
		private String name;
		private String lName;
		private int age;
		private String state;

		public Person() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			if (!name.isEmpty())
				this.name = name;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			if (!name.isEmpty())
				this.lName = lName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			if (age < 150)
				this.age = age;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			if (!state.isEmpty())
				this.state = state;
		}

		// Name: Mickey Mouse
		// Age: 35 years
		// State: Arizona State
		//toString method overridden to output in the necessary format
		@Override
		public String toString() {
			return "Name: " + name + " " + lName + "\nAge: " + age + " years \nState: " + state + " State \n";
		}

	}// use scanner it has next line

	public static void main(String[] args) {
		Exercise20 e = new Exercise20();
		ArrayList<Person> ps = new ArrayList<Person>();
		File file = new File("Data.txt");
		StringBuilder line = new StringBuilder();
		int i = 0;
		//Using scanner to the read from file, done inside the try to enable AutoClosable and verify that there's
		// a file to open.
		try (Scanner sc = new Scanner(file)) {
			//Cycle through the file, first verifying that there's a next line. 
			while (sc.hasNextLine()) { 
				//Add a new Person to the ArrayList in each iteration of the while loop
				ps.add(e.new Person());
				line.delete(0, line.length());
				
				//Store each line in the text file inside a String
				line.append(sc.nextLine());
				
				//Use string manipulation and the Person's class to store each person value on the file.
				int n = 0;
				int last = line.indexOf(":", n);
				ps.get(i).setName(line.substring(n, last));

				n = last + 1;
				last = line.indexOf(":", n);
				ps.get(i).setlName(line.substring(n, last));

				n = last + 1;
				last = line.indexOf(":", n);
				ps.get(i).setAge(Integer.parseInt(line.substring(n, last)));

				n = last + 1;
				last = line.length();
				ps.get(i).setState(line.substring(n, last));
				i++;
			}
		//If file not found show stack trace
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		//Lastly print the ArrayList using the Person method toString for each element.
		for(Person p : ps) {
			System.out.println(p.toString());
		}
		
	}
}
