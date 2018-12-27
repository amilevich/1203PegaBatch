package com.ex.instanceofop;

/*
 * Example of defining many classes in one file
 * 
 * NOTE: only one class can be 'public' and that class 
 * 		 must be the same name as the file
 */
class AClass{}
class BClass extends AClass{}

public class InstanceOfOp {
	
	public static void main(String[] args) {
		
		AClass a = new AClass();
		BClass b = new BClass();
		
		if(b instanceof BClass){ //true
			System.out.println("b instanceof BClass");
		}
		
		if(a instanceof AClass){ //true
			System.out.println("a instanceof AClass");
		}
		
		if(b instanceof AClass){ //true
			System.out.println("b instanceof AClass");
		}
		
		if(a instanceof BClass){ //false
			System.out.println("a instanceof BClass");
		}
		
		/*
		 * Every object is instanceof Object
		 */
		if(a instanceof Object){
			System.out.println("Always true");
		}
		if(b instanceof Object){
			System.out.println("Always true");
		}
		
		
	}
}
