package com.ex.sorting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaturalOrdering {
	public static void main(String[] args) {
		
		List<Student> students = new ArrayList<>();
		students.add(new Student(3,  "John",     3.5, LocalDate.of(1970, 2,  12)));
		students.add(new Student(17, "Amy", 	 3.1, LocalDate.of(1968, 12, 21)));
		students.add(new Student(1,  "Austin", 	 3.9, LocalDate.of(1990, 2,  12)));
		students.add(new Student(2,  "Katie", 	 4.0, LocalDate.of(1990, 2,  11)));
		students.add(new Student(4,  "Courtney", 3.1, LocalDate.of(1990, 1,  1)));
		
		
		
		
		printListPretty(students); //print list
		
		/*
		 * Two examples on how to sort by "natural ordering" using the Comparable interface
		 * 
		 * 		1) Collections.sort(list) OR Collections.sort(list, null)
		 * 		2) list.sort(null)
		 */
//		Collections.sort(students); 	//Most people will ask about this
		students.sort(null); 			//Since Java 1.8 (better optimized)
//		
		printListPretty(students); //print list
		
	}
	
	
	static void printListPretty(List<Student> list){
		System.out.println("Students: ");
		for(Student stud : list){
			System.out.println(stud);
		}
		System.out.println();
	}
}
