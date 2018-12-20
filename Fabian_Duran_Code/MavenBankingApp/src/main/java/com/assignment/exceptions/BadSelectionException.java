package com.assignment.exceptions;

public class BadSelectionException extends Exception{
	public BadSelectionException(){
		super("The input was out scope of the selectable options!");
	}

}
