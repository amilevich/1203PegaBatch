package com.assignment_one.question_11_p1;

import com.assignment_one.question_11_p2.CrossPackageVar2;

// Q11. Write a program that would access two float-variables from a class that exists in another package.
// Note, you will need to create two packages to demonstrate the solution.
// Other package = com.assignment_one.question_11_p2
public class CrossPackageVar {

	public static void main(String[] args) {
		// declares/initializes/instantiates an object of type CrossPackageVar2 which is imported from another package
		CrossPackageVar2 shared = new CrossPackageVar2();
		// sharedVar and anotherSharedVar are public data members, allowing this class to access them freely
		System.out.println(shared.sharedVar);
		System.out.println(shared.anotherSharedVar);

	}

}
