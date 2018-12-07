package com.example.javaquestions;

import com.example.javaquestion11.Numbers;

public class Q11 {
	// Q11. Write a program that would access two float-variables from a class that
	// exists in another package. Note, you will need to create two packages to
	// demonstrate the solution.
	public static void main(String[] args) {
		System.out.println(Numbers.getFloat1() + ", " + Numbers.getFloat2());

	}

}
