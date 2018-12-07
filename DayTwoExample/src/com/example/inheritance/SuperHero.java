package com.example.inheritance;

public class SuperHero {

	// final on a variable means
	// it cannot be changed
	final int i = 3;

	// final on a class
	// cannot have children

	// final on a method means
	// it cannot be overridden

	String name = "Bilboh";
	String ability = "Reading minds";
	String alias = "Nathan";
	String weakness = "Nicknames";

	public SuperHero() {
		// System.out.println("In SuperHero constructor()");
	}

	public SuperHero(String a) {
		// System.out.println("In SuperHero constructor(String)");
	}
	
	public void alterWeakness() {
		weakness = "questions ????????????";
	}

	// public String getName() {
	// return name;
	// }

	public void setName(String name) {
		this.name = name;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public Object getName() {
		return name;
	}
	// covariant return types
	// covariance
	// can only go narrower,
	// not the other way around
	// Object -> String

}
