package com.example.keywords;

public class Main {

	// public - access modifier, can be invoked from anywhere
	// static - implies that the variable or method
	
	// belong to the class, not individual instances. one copy.

	// void - method does not return anything/no value
	// args - method can take command line arguments
	// as an array of Strings
	// does not work without args :(

	public static void main(String[] args) {

		Animal animal = new Animal();
		animal.setWeight(20);
		animal.setName("Bilboh");
		animal.setSpeed(8000);

		System.out.println("The animal's name is" + animal.getName());
		System.out.println(animal.getWeight());
		System.out.println("Hello\"");
		//escape character
	}
	
	

	// object scope example (static)
}
