package com.example.inheritance;

// polymorphism
// implementation of polymorphism in Java
// is overriding and overloading

// overloading?
// there are two or more methods in a class
// with the same method name, but different
// parameters
// compile time polymorphism

// overriding?
// when a method in a child class has the same
// name as a method in a parent class,
// however with different implementation.
// same method name, same parameters.
// runtime polymorphism

public class SuperVillain extends SuperHero {

	// "extends" -> if we inherit from a class
	// "implements" -> if we inherit from an interface

	String alias = "Bacon Pancake Junior";
//	String capeColor = "Black";

	// shadowing
	// refers to the practice of using two
	// variables with the same name within
	// scopes that overlap.
	// when you do that, the variable with
	// the higher scope is hidden because
	// the variable with the lower scope
	// "shadows" it

	// from within the class super.something
	// and cast it to the parent class

	// casting?
	// taking an object of one particular
	// type and turning it into another
	// object type

	// upcasting - casting a subtype to a
	// supertype (upwards the inheritance tree)

	// downcasting - casting to a subtype,
	// (downward the inheritance tree)

	// int x = 3;
	// double xx = (double)x;
	
	public SuperVillain() {
//		System.out.println("In SuperVillain constructor()");
	}
	
	@Override
	public void alterWeakness() {
		weakness = "being called on";
	}
	
	@Override
	public String getName() {
		return new String();
	}

}
