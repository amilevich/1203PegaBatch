package com.assignment.weekone;

import java.util.Comparator;

public class Q7Sortname implements Comparator <Q7Employee> {//interface!
//comparator used to sort name
	@Override
	public int compare(Q7Employee o1, Q7Employee o2) {//compares one employee entry and a second entry
		return o1.Name.compareTo(o2.Name);//returns 1 if o1 comes first, 0 if equal and -1 if o1 comes after o2
	}

}
