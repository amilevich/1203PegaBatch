package com.questioneleven;

public class Question11Access {

	// a public access modifier would allow direct access to these variables,
	// but this is bad practice because it breaks encapsulation
	private float accessOne = 1.11f;
	private float accessTwo = 2.22f;
	
	public Question11Access() {
		
	}
	
	public float getAccessOne() {
		return accessOne;
	}
	public void setAccessOne(float accessOne) {
		this.accessOne = accessOne;
	}
	public float getAccessTwo() {
		return accessTwo;
	}
	public void setAccessTwo(float accessTwo) {
		this.accessTwo = accessTwo;
	}
	
	
	
}
