package com.assignment.weekone;

import java.util.ArrayList;

public class Q12 {

	public static void main(String[] args) {
		ArrayList<Integer>list = new ArrayList<Integer>();
		for (int i = 1; i <=100;i++) {//populates arraylist from 1 to 100
			list.add(i);
		}
		for(Integer n : list) {//using enhanced for loop!
			if (n%2==0)
			System.out.println(n);//only prints out even numbers
		}
	}

}
