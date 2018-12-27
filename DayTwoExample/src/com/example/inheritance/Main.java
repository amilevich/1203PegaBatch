package com.example.inheritance;

public class Main {
	
	private static SuperHero superVillain;

	public static void main(String[] args) {

		SuperHero superHero = new SuperHero();
		SuperVillain sV = new SuperVillain();
		
		SuperHero sH = new SuperVillain();
		// upcasting
		// every SuperVillain is a SuperHero
		// but every SuperHero is not a
		// SuperVillain
		
//		System.out.println(sH.alias);
//		System.out.println(((SuperVillain)sH).alias);
		// casting
		// sH is an object of SuperHero type
		// even though the new keyword is paired
		// with SuperVillain
		
		sH.alterWeakness();
		System.out.println(sH.weakness);
		// being called on 
		System.out.println(((SuperVillain)sH).weakness);
		// being called on
		
	}

}
