package com.homework.q9;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> prime = new ArrayList<>();
		
		int x = 100;
		
		boolean check [] = new boolean[x];
		
		for(int i = 0; i < x; i++) {
			
				prime.add(i+1);
				check[i] = true;
		}
		
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
                System.out.print(prime.get(i-1) + " "); 
        } 
	}
}
