package com.test.asmt01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q07Main {
	
	public static void main(String[] args) {
	
		// Populate an ArrayList of Employees (name, dept, age).
		List<Q07Employee> empList = new ArrayList<Q07Employee>(); 
	    empList.add(new Q07Employee("Munster, Herman", "accounting", 99)); 
	    empList.add(new Q07Employee("Flintstone, Fred", "payroll", 33)); 
	    empList.add(new Q07Employee("Smart, Maxwell", "it support", 44)); 
	    
	    // Print the unsorted ArrayList.
	    System.out.println("Q07Employee - Unsorted"); 
        for (int i = 0; i < empList.size(); i++) { 
            System.out.println(empList.get(i));
        }
        
        // Sort the ArrayList by Name.
        Collections.sort(empList, new Q07SortByName());
	    
        // Print the ArrayList sorted by Name.
	    System.out.println("\nQ07Employee - Sorted by Name"); 
        for (int i = 0; i < empList.size(); i++) { 
            System.out.println(empList.get(i));
        }
        
        // Sort the ArrayList by Dept.
        Collections.sort(empList, new Q07SortByDept());
	    
        // Print the ArrayList sorted by Dept.
	    System.out.println("\nQ07Employee - Sorted by Department"); 
        for (int i = 0; i < empList.size(); i++) { 
            System.out.println(empList.get(i));
        }
        
        // Sort the ArrayList by Age.
        Collections.sort(empList, new Q07SortByAge());
	    
        // Print the ArrayList sorted by Age.
	    System.out.println("\nQ07Employee - Sorted by Age"); 
        for (int i = 0; i < empList.size(); i++) { 
            System.out.println(empList.get(i));
        }
	    
	}

}
