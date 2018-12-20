package com.assignment.exceptions;

public class NotYourAccountException extends Exception {
	public NotYourAccountException() {
		super("User does not own that account!");
	}

}
