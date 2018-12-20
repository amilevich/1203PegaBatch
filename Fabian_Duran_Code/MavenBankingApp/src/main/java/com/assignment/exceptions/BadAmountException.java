package com.assignment.exceptions;

public class BadAmountException extends Exception{
	public BadAmountException() {
		super("That is an incorrect amount for the selected operation!");
	}

}
