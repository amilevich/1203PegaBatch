package com.homework.q19;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
ArrayList<Integer> prime = new ArrayList<>();
		
		int x = 10;
		
		boolean check [] = new boolean[x];
		int result = 0;
		
		for(int i = 0; i < x; i++) {
			
				prime.add(i+1);
				check[i] = true;
				System.out.print(prime.get(i) + " ");
		}
		
		Iterator<Integer> list = prime.iterator();
		
		for(int i = 0; i < x; i++) {
			if(prime.get(i)%2 == 0)
				result += prime.get(i);
		}
		
		System.out.println();
		System.out.println("Even numbers added: " + result);
		result = 0;
		
		for(int i = 0; i < x; i++) {
			if(prime.get(i)%2 != 0)
				result += prime.get(i);
		}
		System.out.println("Odd numbers added: " + result);
		
		for(int i = 2; i*i < x; i++) {
			
			if(check[i-1]) {
				
				for(int j = i*i; j<x; j += i) {
					check[j-1] = false;
				}
			}
		}
		
		for(int i = 2; i < x; i++) 
        { 
            if(check[i-1]) 
            {	System.out.println(prime.get(i-1) + " ");
                //prime.remove(new Integer((i-1)));
            }	 
        } 
		
		int count = 0;
		
		while(list.hasNext())
		{
			list.next();
			if(check[count]) {
				list.remove();
			}
			
			if(count < x)
				count++;
		}
//		for(int i = 0; i < prime.size(); i++) {
//			System.out.print(prime.get(i) + " ");
//		}
		System.out.println(prime);
	}

}
