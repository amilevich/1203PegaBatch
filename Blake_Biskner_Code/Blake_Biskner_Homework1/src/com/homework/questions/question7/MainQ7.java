package com.homework.questions.question7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Driver Method for Q7
 * @author Blake Biskner
 * @version 1.7
 */

public class MainQ7 {
	
	public static void main(String[] args) {
		EmployeeQ7 empA=new EmployeeQ7("Al","Security",20);
		EmployeeQ7 empB=new EmployeeQ7("Bob","Customs",95);
		
		List<EmployeeQ7> empList=new ArrayList<EmployeeQ7>();
		empList.add(empA);
		empList.add(empB);
		
		Iterator<EmployeeQ7> itr=empList.iterator(); // Instantiate iterator
		
		System.out.println("Employees Ordered by Name");
		Collections.sort(empList,  new OrderNameQ7());
		itr=empList.iterator(); // Ensure that iterator is referring to sorted array
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}

		
		System.out.println("Employess Ordered by Department");
		Collections.sort(empList, new OrderDeptQ7());
		itr=empList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		System.out.println("Employees Ordered by Age");
		Collections.sort(empList, new OrderAgeQ7());
		itr=empList.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
