package com.homework.questions.question7;

import java.util.Comparator;
/**
 * Name Comparator
 * @author Blake Biskner
 * @version 1.7
 */

public class OrderNameQ7 implements Comparator<EmployeeQ7> {
	public int compare(EmployeeQ7 emp1, EmployeeQ7 emp2) {
		return (emp1.getName()).compareTo((emp2.getName())); // Lexicographically compares strings
	}

}
