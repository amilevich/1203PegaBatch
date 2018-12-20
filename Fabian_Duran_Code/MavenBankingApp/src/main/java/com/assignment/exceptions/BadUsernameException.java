package com.assignment.exceptions;

public class BadUsernameException extends Exception{
	public BadUsernameException() {
		super("Incorrect Username!");
	}
}
