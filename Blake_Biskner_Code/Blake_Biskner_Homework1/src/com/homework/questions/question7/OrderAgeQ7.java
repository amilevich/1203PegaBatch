package com.homework.questions.question7;

import java.util.Comparator;

/**
 * Age Comparator
 * @author Blake Biskner
 * @version 1.7
 */

public class OrderAgeQ7 implements Comparator<EmployeeQ7> {
	public int compare(EmployeeQ7 emp1, EmployeeQ7 emp2) {
		if(emp1.getAge()>emp2.getAge()) {
			return 1; // Return positive int if emp1>emp2 in accordance with sort()
		}else if(emp1.getAge()<emp2.getAge()) {
			return -1; // Return negative int if emp1<emp2 in accordance with sort()
		}else {
			return 0; // Return 0 if equal in accordance with sort
		}
	}
}
