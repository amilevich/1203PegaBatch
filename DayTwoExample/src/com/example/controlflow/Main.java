package com.example.controlflow;

public class Main {

	// control flow
	// break up the flow of execution
	// by employing decision making
	// and looping, enabling the program
	// to conditionally execute particular
	// blocks of code

	// if -> runs block of code if true
	// "else if" is used to write mutually
	// exclusive blocks of code
	// "else" will execute if nothing else did.

	// for -> typically used when you know how long
	// you want to loop

	// while -> typically used when you do not know
	// how long you want to loop

	// do while -> same as while, but will
	// definitely run at least once

	// switch -> useful when you are checking a
	// variable against a lot of values

	// "break" -> end loop, can only be used in loops and switch
	// "continue" -> go to end of the loop and starts
	// the next iteration, only in loops

	public static void main(String[] args) {

		int tempWhile = 0;

		while (tempWhile < 12) {
			tempWhile++;
			// System.out.println("tempWhile in while loop is " + tempWhile);
		}
		// execute loop as long as condition is
		// true

		int arrayOne[] = { 10, 25, 15, 12, 5 };

		for (int i = 0; i < arrayOne.length; i++) {
			System.out.println(2*arrayOne[i]);
		}
		
		// & - and
		// && - short-circuit and
		// | - or
		// || - short-circuit or
		
		// short-circuit -> only checks the first condition
		// meaning they don't evaluate the right hand side
		// if it isn't necessary
		// the & and | operators, when used as logical operators,
		// always evaluate both sides
		
		// false && -> it is not necessary to know what
		// the right hand size is, the result must be false
		
//		while(boolOne && boolThree) {
//			// will only go into the code block
//			// if the initial condition is true
//			// if the condition is false, it will
//			// go past the loop
//			//if the condition is still true, it will
//			//execute again
//		}
//		
//		do {
//			// will run at least once
//			// if the condition is false, it will go
//			// past the loop
//			// if the condition is still true,
//			// it will execute the block again
//		}
	}

}
