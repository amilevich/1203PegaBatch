package com.example.increment;

public class Test {
	
	public static void main(String[] args) {
		
		char c = 'a';
		// single quotes are for characters
		// double quotes are for strings
		
//		int i = 2;
		
//		i=c;
//		c=i; // type mismatch, cannot convert from int to char
		
		char c2 = 97;

		c2=63;
//		System.out.println(c2);
		// ln makes a new line, remove it
		// to print things next to each other
		
		// every character has an assumed integer value
	
		int i = 300;
		System.out.println(i+i+"Blue"); //600Blue
		System.out.println(i+"Blue"); //300Blue
		System.out.println("Red"+i+i); //Red300300
		
		// order of operations!!! left to right!
	}

}
