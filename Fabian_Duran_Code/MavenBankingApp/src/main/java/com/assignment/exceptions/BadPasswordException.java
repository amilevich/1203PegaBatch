package com.assignment.exceptions;

public class BadPasswordException extends Exception {
	public BadPasswordException() {
		super("Incorrect Password!");
	}
}
