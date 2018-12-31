package com.questions.fibonacci;
//Author: Steven Jean-Paul
//Q2. Fibanocci
public class Fibonacci {

	public static void main(String[] args) {
		int o = 1;
		int z = 0;
		int f = 0; //Set variables o, z, f to use to change/store positions to calculator higher fibanocci values. 
		
		System.out.println(z + "\n" + o);
		
		for(int i = 0; i < 25; i++) {
			f = z + o; //1
			
			z = o;
			o = f;
			System.out.println(f);
		}
	}
}
