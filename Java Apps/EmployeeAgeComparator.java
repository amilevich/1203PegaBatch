package com.questions.comparator;

import java.util.Comparator;
//Steven Jean-Paul
//Q7 - Comparator
public class EmployeeAgeComparator implements Comparator<Employee> {
	@Override //Override Comparator interface
	public int compare(Employee e1, Employee e2){ //Enter objects of employee type into the compare method.
		int e1Age = e1.getAge(); //Any employee param passed into this compare method will be compared to the second param
		int e2Age = e2.getAge(); //and then the employee object with the higher age will dictate the int value passed back
								//to indicate which is older/higher.
		if(e1Age > e2Age){
			return 1;
		}
		else if(e1Age < e2Age){
			return -1;
		}
		else{
			return 0;
		}
	}
}