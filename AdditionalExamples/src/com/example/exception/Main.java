package com.example.exception;

public class Main {
	
	/*
	 * if the JVM sees an exception, the program will exit
	 * specifically, a runtime exception doesn't need to be dealt
	 * with for the code to compile.
	 * 
	 * Exception is "thrown" when something goes wrong and
	 * can be "caught" to resolve the problem.
	 */

	public static void main(String[] args) {
		
	//throw new RuntimeException();
		
	try {
		throw new Exception();
	} catch (Exception e) {
		e.printStackTrace();	//do not have to put this
		
		throw new RuntimeException();	// can put exceptions inside of exceptions
	}
	
	//difference between throw and throws?
	// throw - says there is a problem, you can throw anything
			 //that is a Throwable.
	// throws - pass of responsibility to another method,
			 // you can specify many exceptions separated
			 // by commas, another way to handle a checked exception.
	
	

	


	}

}
