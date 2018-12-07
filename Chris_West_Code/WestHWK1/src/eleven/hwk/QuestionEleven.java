package eleven.hwk;

import another.hwk.NotAnotherPackageMovie;

public class QuestionEleven {
	/*
	 * The problem is Question 11:
	 * 
	 * Write a program that would access two float-variables from a class that
	 * exists in another package. Note, you will need to create two packages to
	 * demonstrate the solution.
	 * 
	 */

	/*
	 * (1) Accessing and printing the values from another package
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) { // Look at (1)

		System.out.println(NotAnotherPackageMovie.numberOne + " " + NotAnotherPackageMovie.numberTwo);
	}

}
