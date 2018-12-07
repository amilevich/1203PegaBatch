package com.test.asmt01;

import java.util.Comparator;

public class Q07SortByDept implements Comparator<Q07Employee> {

    // Used for sorting in ascending order of Employee's Name.
    public int compare(Q07Employee emp1, Q07Employee emp2) 
    { 
    	return emp1.dept.compareTo(emp2.dept); 
    } 

}
