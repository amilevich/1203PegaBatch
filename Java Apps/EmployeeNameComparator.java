package com.questions.comparator;

import java.util.Comparator;
//Steven Jean-Paul
//Q7 - Comparator

public class EmployeeNameComparator implements Comparator<Employee> { //This class implements the Comparator interface

	@Override
	public int compare(Employee e1, Employee e2){ //Override the Comparator's compare method.
		return e1.getName().compareTo(e2.getName());
	}
}
