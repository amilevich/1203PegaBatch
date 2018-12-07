package com.examples.assigment1;

import com.examples.exercise11.package1.Dummy;

public class Exercise11 {
	// Q11. Write a program that would access two float-variables from a class that
	// exists in another package. Note, you will need to create two packages to
	// demonstrate the solution.

	public static void main(String[] args) {
		//Float variables pi and e accessed from the package com.examples.exercise11.package1
		System.out.println("Values from the imported Class Dummy, pi = " + Dummy.getPi() + " and e = " + Dummy.getE());
	}
}
