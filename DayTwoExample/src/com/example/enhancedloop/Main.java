package com.example.enhancedloop;

import java.util.ArrayList;

public class Main {
	
	//what is an arraylist?
	// similar to an array but the size
	// is not fixed
	// dynamic array
	// ArrayList can not contain primitive
	// data types, it can only contain
	// Objects (Wrapper classes)

	public static void main(String[] args) {

		ArrayList<String> names = new ArrayList<String>();
	
		names.add("David");
		names.add("Bilboh");
		names.add("BoxMan");
		names.add("Cereal, why so cerious");
		names.add("HypeMan");
		names.add("Czharliehz");
		names.add("La-a");
		
		for(String nm : names) {  // "in"
			System.out.println(nm);
		}
		
		// works on arrays and data structures
		// in the Collection API
	}
	

}
