package com.assignment.weekone;

import java.util.Comparator;

public class Q7SortAge implements Comparator <Q7Employee> {

	@Override
	public int compare(Q7Employee o1, Q7Employee o2) {
		return o1.Age - o2.Age;//returns postive integer if o1 is after, 0 if equal, and negative integer if o1 comes before
	}

}
