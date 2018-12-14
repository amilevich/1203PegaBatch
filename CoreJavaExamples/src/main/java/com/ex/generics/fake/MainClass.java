package com.ex.generics.fake;

public class MainClass {
	public static void main(String[] args) {
		
		
		NotQuiteGeneric myObj = new NotQuiteGeneric(12);
		/*
		 * 
		 */
		
		
		Integer myValue = (Integer) myObj.getValue();
		System.out.println(myValue);
		
		
		String myValue2 = (String) myObj.getValue();
		
		
		
		
	}
}
