package com.assignment.weekone;

import java.util.Comparator;

public class Q7SortDept implements Comparator <Q7Employee> {//interface

	@Override
	public int compare(Q7Employee o1, Q7Employee o2) {
		return o1.Department.compareTo(o2.Department);//returns 1 if o1 comes first, 0 if equal and -1 if o1 comes after o2
	}

}
