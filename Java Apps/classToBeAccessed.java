package com.questions.floatvars;
//Author: Steven Jean-Paul
//Q11 - Access Floats
public class classToBeAccessed { //Create a class with concrete methods.
	private float pocketMoney = 88.25f;      //Set private fields
	private float creditCardCredit = 3245.44f;
	
	public float getPocketMoney() {
		return this.pocketMoney;
	}
	public void setPocketMoney(float pocketMoney) {
		this.pocketMoney = pocketMoney;
	}
	public float getCreditCardCredit() {
		return this.creditCardCredit;
	}
	public void setCreditCardCredit(float creditCardCredit) {
		this.creditCardCredit = creditCardCredit;
	}
	
	
}
