package com.ex.generics;

class ActualGenericClass<T> {
	private T value;
	
	public ActualGenericClass(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
}

public class ActuallyGeneric {
	public static void main(String[] args) {
		
		ActualGenericClass<Integer> genericClass = new ActualGenericClass<Integer>(12);
//		ActualGenericClass<Integer> genericClass2 = new ActualGenericClass<Integer>("hello world"); //compile time error
//		ActualGenericClass<Integer> genericClass = new ActualGenericClass<>(12); 					//Diamond syntax. Since Java 1.7
		Integer integer = (Integer) genericClass.getValue();
		System.out.println(integer);
		
//		String str = (String) genericClass.getValue();	//compile time error
//		System.out.println(str);		
		
	}
}
