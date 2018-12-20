package com.assignment.exceptions;

public class InaccessibleAccountException extends Exception{
	public InaccessibleAccountException() {
		super("That account is inaccessible!");
	}
}
