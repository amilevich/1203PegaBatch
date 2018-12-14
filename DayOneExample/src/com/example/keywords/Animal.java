package com.example.keywords;

public class Animal {

	public String name; // variables should be private
	public int speed;
	public int weight;

	// what is a constructor?
	// they return an instance of a class
	// do not have a return type
	// not really a method <Matt TM>
	// if no constructor is explicitly provided,
	// the JVM provides a constructor by default

	public Animal() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// getters(accessors)/setters(mutators)
	// process of encapsulation

	// access modifiers
	// restricts access to variables/methods/certain members/etc
	// "private" - class itself
	// default - class itself, package
	// "protected" - class itself, package, children
	// "public" - class itself, package, children, everything

	// Scopes of a variable: where the variable exists
	// a variable's reference will only exist within
	// the context of its declared scope, which is
	// based on the location of its declaration

	// class/static - exists within the class itself
	// instance/object - only exists within the specific object
	// method/local - only exists within the specific method
	// block - only exists within the specific block
	// (if, for, while, do while, etc)

	// instantiation - ("new" keyword) when the memory
	// is allocated for an object.
	// initialization - when values are put into the memory
	// that was allocated
	// declaration - when you state to the program
	// that there will be an object of a certain type
	// existing and what the name of the object will be

	// Animal a = null; // declaring and initializing 
	
}
