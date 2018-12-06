package com.example.interfaces;

public interface Person {

	int num=0;
	// variables inside an interface
	// are inherently public, static, final
	
	void walk();
	// methods inside an interface are
	// inherently abstract
	
	default void eat() {
		// you can use default keyword
		// to give concrete
		// implementation
	}
}
