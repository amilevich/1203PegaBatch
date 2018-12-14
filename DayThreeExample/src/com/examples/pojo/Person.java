package com.examples.pojo;

/*
 *  POJO - Plain Old Java Object
 *  	Technically any Java Class that does NOT have an entry point (main method)
 *  	But most people will refer POJOs as classes that have:
 *  		-state (variables)
 *  		-behavior (methods)
 *  		-constructor
 *  		-toString
 */
public class Person {

	// State
	private String name;
	private int age;
	
	// no-args constructor
	public Person() {
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	// alt + shift + s = generated list
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	/**
	 * Age parameter cannot be set higher than 150 or IllegalArgumentException
	 * 	will be thrown and age will be set to 18
	 * @param age
	 */
	public void setAge(int age)  {
		if(age > 150) {
			this.age = 18;
			throw new IllegalArgumentException();
		}
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}






