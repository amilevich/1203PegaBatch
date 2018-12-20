package com.assignment.exceptions;

public class ActiveAccountException extends Exception{
	public ActiveAccountException() {
		super("This account is still active!");
	}
}
