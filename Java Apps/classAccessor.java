package com.questions.accessfloatvars;
import com.questions.floatvars.*;

//Author: Steven Jean-Paul
//Q11 - Access Floats

public class classAccessor{

	public static void main(String[] args) { //Uses other classes and objects to access float values.
		classToBeAccessed thisGuy = new classToBeAccessed();
		System.out.println(thisGuy.getPocketMoney());
		System.out.println(thisGuy.getCreditCardCredit());
	}

}
