package com.assignment.exceptions;

public class OverdrawnException extends Exception {
	public OverdrawnException() {
		super("Exceeds Account limit!");
	}

}
