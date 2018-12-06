package com.ex.generics;

/*
 * This class IS 'generic' but not type safe
 */
class GenericClass {
	private Object value;
	
	public GenericClass(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
	
}

public class TryingToBeGeneric {
	
	public static void main(String[] args) {
		
		GenericClass genericClass = new GenericClass(12);
		Integer integer = (Integer) genericClass.getValue();
		System.out.println(integer);
		
		String str = (String) genericClass.getValue();	//ClassCastException
		System.out.println(str);
	}
}
