package com.ex.generics.actually;

public class ActuallyGeneric <T extends Number> {
	
	private T value;
	
	public ActuallyGeneric(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	
}
