package com.homework.q3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String reverse = "Gotcha!";
		
		for(int i = 0; i < reverse.length(); i++) {
			reverse = reverse.substring(1, reverse.length() - i) + reverse.substring(0,1) 
			+ reverse.substring(reverse.length() - i, reverse.length());
			//System.out.print(reverse.charAt(reverse.length()-i-1));
		}
		
		System.out.println(reverse);
	}

}
