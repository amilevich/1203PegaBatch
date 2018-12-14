package com.ex.equalsop;

import com.pojos.Person;

public class EqualsOpVsEqualsMethod {
	
	public static void main(String[] args) {
		/*
		 * == works fine for primitives
		 */
		int x = 5;
		int y = 5;
		int z = 6;
		
		if(x == y)
			System.out.println("x == y");
		else
			System.out.println("x != y");
		
		if(x == z)
			System.out.println("x == z");
		else
			System.out.println("x != z");
			
		
		/*
		 * == does not work the same way for objects
		 * 
		 * Person must override the 'equals' method from Object class
		 * 
		 */
		Person p1 = new Person("John", 28);
		Person p2 = new Person("John", 28);
		
		if(p1 == p2)
			System.out.println("p1 == p2");
		else
			System.out.println("p1 != p2");
		
		if(p1.equals(p2))
			System.out.println("p1.equals(p2)");
		else
			System.out.println("!p1.equals(p2)");
		
		
	}

}
