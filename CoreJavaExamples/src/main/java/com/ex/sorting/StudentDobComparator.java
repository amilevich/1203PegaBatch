package com.ex.sorting;

import java.util.Comparator;

public class StudentDobComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getDob().compareTo(o2.getDob());
	}

}
