package com.ex.casting;

class Parent{}
class Child extends Parent{}
class GrandChild extends Child{}

public class CastingObjects {
	public static void main(String[] args) {
		
		Parent parent1 = new Child();
		Parent parent2 = new GrandChild();

		/*
		 * Explicit/Down Casting
		 * 
		 * Must specify cast because it could cause a problem.
		 * 
		 * Also, forcing the developer to cast like this ensures that
		 * the developer make sure they know what they're doing
		 */
		Child child1 = (Child) parent1;
		Child child2 = (Child) parent2;
		
		GrandChild gchild1 = (GrandChild) parent1; //ClassCastException
		GrandChild gchild2 = (GrandChild) parent2;
		
		
		
		
	}
}
