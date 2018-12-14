package com.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {

	// Reflection API
	// library that allows us to inspect
	// and/or modify runtime attributes
	// of classes, interfaces, fields,
	// and methods.
	// used mostly for debugging purposes
	// or for information purposes
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		// creating object whose property is to be checked
		Bilboh obj = new Bilboh();

		// creating class object from the object using GetClass method
		Class cls = obj.getClass();
		// System.out.println("The name of the class is: " + cls.getName());

		// getting the constructor of the class through
		// the object of the class
		Constructor con = cls.getConstructor();
		// System.out.println("The name of the constructor is: " + con.getName());

		// getting the methods of the class through
		// the object of the class by using
		// getMethods

		Method[] methods = cls.getMethods();
		System.out.println("The public methods of the class are: ");

		for (Method m : methods) {
			System.out.println(m.getName());
		}

	}
}
