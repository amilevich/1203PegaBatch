package com.homework.questions;

/**
 * Command Line String Reader
 * @author Blake Biskner
 * @version 1.16
 */

public class Question_16{
	
	public static void main(String args[]) {
		if(args.length>0) { // Ensure String is input
			System.out.println(args[0].length());
		} else {
			throw new ArrayIndexOutOfBoundsException(); // Ensure String is input to command line
		}
	}
}
