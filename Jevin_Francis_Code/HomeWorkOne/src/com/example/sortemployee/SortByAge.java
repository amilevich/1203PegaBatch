package com.example.sortemployee;

import java.util.Comparator;

public class SortByAge implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getAge() > o2.getAge()) {
			return 1;
		} else if (o1.getAge() < o2.getAge()) {
			return -1;
		} else if (o1.getAge() == o2.getAge()) {
			return 0;
		} else {
			return -2;
		}
	}

}
