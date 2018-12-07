package com.assignment.weekone;

import java.util.ArrayList;

public class Q9 {
	public static boolean isPrime(int n) {
		if (n==2||n == 3|| n == 5|| n==7||n==11)//compensates for initials primes
			return true;
		else if (n%2 == 0)//removes even numbers
			return false;
		for (int i = 2; i < n; i++) {//if its perfectly divisble-> return false
			if (n%i ==0)
				return false;
		}
		//System.out.println(n);
		return true;
	}

	public static void main(String[] args) {
		ArrayList <Integer> list = new ArrayList<Integer>();
		for (int i = 1;i<=100;i++)
			list.add(i);
		//System.out.println((5%1)+" "+(5%5));
		System.out.println("These are the prime numbers from 1 to 100");
//		for (int i = 1; i <=list.size();i++) {
//				System.out.println(i+", ");}
		for (int i = 1; i <list.size();i++) {//runs through array list searching for primes, adds primes to new array list
			if (isPrime(list.get(i)))
				System.out.println(list.get(i));//prints new list
		}
	}

}
