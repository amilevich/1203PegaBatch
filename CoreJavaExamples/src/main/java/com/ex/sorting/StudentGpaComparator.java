package com.ex.sorting;

import java.util.Comparator;

public class StudentGpaComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		double o1Gpa = o1.getGpa();
		double o2Gpa = o2.getGpa();
		
		if(o1Gpa > o2Gpa){
			return 1;
		}else if(o1Gpa < o2Gpa){
			return -1;
		}else {
			return 0;
		}
	}
	
}
