package com.assignment.weekone;

import java.util.ArrayList;

public class Q19 {

	public static void main(String[] args) {
		int sum = 0; //will be used in displaying sum of evens and odds
		ArrayList <Integer> list = new ArrayList<Integer>();
		
		for (int i = 1; i <=10; i++) 
			list.add(i);//adds 1 thru 10 to array list
		System.out.println(list.size());
		System.out.print("Here is the list: ");
		for (int i = 0; i < list.size(); i++)//displays array list
			System.out.print(list.get(i) + " ");
		
		System.out.print("\nHere is the sum of the even numbers: ");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)%2==0)
				sum = sum + list.get(i);//sum of even numbers
		}
		System.out.println(sum);
		
		sum = 0;//resets sum
		
		System.out.print("Here is the sum of the odd numbers: ");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)%2!=0)
				sum = sum + list.get(i);//displays sum of odds
		}
		System.out.println(sum);
		
		System.out.print("\nHere is the list without prime numbers: ");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i)==2||list.get(i)==3||list.get(i)==5||list.get(i)==7)//this method isn't really useful for larger ranges of numbers but I did to mostly save on time
					list.remove(i);	//removes primes
			}
			for (int i = 0; i < list.size(); i++)
				System.out.print(list.get(i) + " ");//displays non primes
	}

}
