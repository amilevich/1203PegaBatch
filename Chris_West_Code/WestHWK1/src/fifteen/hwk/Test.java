package fifteen.hwk;

public class Test {
	/*
	 * The problem is Question 15:
	 * 
	 * Write a program that defines an interface having the following methods:
	 * addition, subtraction, multiplication, and division. Create a class that
	 * implements this interface and provides appropriate functionality to carry out
	 * the required operations. Hard code two operands in a test class having a main
	 * method that calls the implementing class.
	 * 
	 */

	/*
	 * (1) So, I'm creating an object from the class QuestionFifteen called solve.
	 * Then I call the methods of that class and pass the variables x and y. I use
	 * the Math.random() to create random numbers for both x and y. Also, I'm using
	 * the printf with a statement that tells the returning value I want it to be 2
	 * decimal places. If I don't I will have a really long decimal number that goes
	 * beyond 2 decimal places.
	 * 
	 * 
	 * (2) So, I'm implementing the interface Mathematical all the methods in the
	 * Mathematical class need to exist here as well. They need to have the same
	 * return, name, parameters. As for the methods return values, considering its
	 * simple math I don't need to write a bunch a code out. All I need to do is the
	 * simple math and have it return its value.
	 * 
	 * (3) This class is an interface. All the methods here are abstract. I have to
	 * use the keyword interface first to establish that its an interface then I
	 * state what the name will be.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		double x = Math.random() * 100, y = Math.random() * 100;
		QuestionFifteen solve = new QuestionFifteen(); // Look at (1)
		System.out.printf("%.2f", solve.addition(x, y));
		System.out.printf("\n%.2f", solve.division(x, y));
	}
}
