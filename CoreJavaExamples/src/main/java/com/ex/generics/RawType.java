package com.ex.generics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Dangers of using raw types.
 * 
 * Raw type is used when you don't specify a type for a generic class
 * 
 * Raw types are still allowed for backward compatibility
 * 
 * Do not use raw types!
 */
public class RawType {
	public static void main(String[] args) {
		
		List list = new LinkedList();
		list.add("First");
		list.add("Second");
		list.add(12); 		//Will cause ClassCastException to be thrown
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}
}
