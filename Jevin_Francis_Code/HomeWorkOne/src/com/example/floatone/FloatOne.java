//Write a program that would access two float-variables from a class that exists in another package. 
//Note, you will need to create two packages to demonstrate the solution.	

package com.example.floatone;

public class FloatOne {
	// Creating the Floats on Separate Package
	private float f1 = 23.4f;
	private float f2 = 4345.4f;
	
	public float getF1() {
		return f1;
	}
	public void setF1(float f1) {
		this.f1 = f1;
	}
	public float getF2() {
		return f2;
	}
	public void setF2(float f2) {
		this.f2 = f2;
	}
	
}
