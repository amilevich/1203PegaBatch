package com.ex.sorting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
public class UnnaturalOrdering {
	public static void main(String[] args) {
		/*
		 *  Comparable - default/natural/primary ordering
		 *  	StudentId is the chosen Comparable for natural/default orderings 
		 *  
		 *  Comparator - 3rd party object that will allow for alternate ordering
		 * 		We made 3 other classes for alternate sorting
		 * 			 StudentNameComparator
		 * 			 StudentGpaComparator
		 * 			 StudentDobComparator
		 */
		
		List<Student> students = new ArrayList<>();
		students.add(new Student(3,  "John",     3.5, LocalDate.of(1970, 2,  12)));
		students.add(new Student(17, "Amy", 	 3.1, LocalDate.of(1968, 12, 21)));
		students.add(new Student(1,  "Austin", 	 3.9, LocalDate.of(1990, 2,  12)));
		students.add(new Student(2,  "Katie", 	 4.0, LocalDate.of(1990, 2,  11)));
		students.add(new Student(4,  "Courtney", 3.1, LocalDate.of(1990, 1,  1)));
		
		printListPretty(students);
		Collections.sort(students);
		printListPretty(students);
		Collections.sort(students, new StudentNameComparator());
		printListPretty(students);
		
		Collections.sort(students, new StudentGpaComparator());
		printListPretty(students);
		Collections.sort(students, new StudentDobComparator());
		printListPretty(students);
		
	}
	
	static void printListPretty(List<Student> list){
		System.out.println("Students: ");
		for(Student stud : list){
			System.out.println(stud);
		}
		System.out.println();
	}
}
