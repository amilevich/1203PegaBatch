package com.ex.generics.actually;

public class MainClass {
	public static void main(String[] args) {
		
		ActuallyGeneric<Integer> myObj = new ActuallyGeneric<>(12);
		
		Integer myValue = myObj.getValue();
		
		
		
//		String str = (String) myObj.getValue();
		
		
//		ActuallyGeneric<String> myObject = new ActuallyGeneric<String>("hello world");
		
		
		
	}
}
