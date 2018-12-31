package com.questions.comparator;

import java.util.Comparator;
//Steven Jean-Paul
//Q7 - Comparator
public class EmployeeDepartmentComparator implements Comparator<Employee> {
//This class is the same as the EmployeeAgeComparator
	@Override
	public int compare(Employee e1, Employee e2){
		return e1.getDepartment().compareTo(e2.getDepartment());
	}
}