package com.homework.q12;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int x = 100;
		int prime[] = new int[x];
		
		for(int i = 0; i < x; i++) {
			
				prime[i] = i+1;
		}
		
		for(int i : prime) {
			if(i%2 == 0) {
				System.out.print(i + " ");
			}
		}

	}

}
