package com.homework.questions.question7;

import java.util.Comparator;
/**
 * Department Comparator
 * @author Blake Biskner
 * @version 1.7
 */

public class OrderDeptQ7 implements Comparator<EmployeeQ7>{
	public int compare(EmployeeQ7 emp1, EmployeeQ7 emp2) {
		return((emp1.getDept()).compareTo(emp2.getDept())); // Lexicographically compares departmnet names
	}
}
