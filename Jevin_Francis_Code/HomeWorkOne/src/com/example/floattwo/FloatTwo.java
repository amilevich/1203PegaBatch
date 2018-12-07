//Write a program that would access two float-variables from a class that exists in another package. 
//Note, you will need to create two packages to demonstrate the solution.	

package com.example.floattwo;

import com.example.floatone.FloatOne;

public class FloatTwo {
	public static void main(String[] args) {
		//Accessing the Float from another Package by Importing the pacage
		//And accessing by creating an object of the class
		FloatOne f = new FloatOne();
		System.out.println("Float Number One: " + f.getF1());
		System.out.println("Float Number Two: " + f.getF2());
	}
}
