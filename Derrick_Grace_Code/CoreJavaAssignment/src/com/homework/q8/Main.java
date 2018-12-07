package com.homework.q8;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> sPalindrome = new ArrayList<>();
		
		sPalindrome.add("karan");
		sPalindrome.add("madam");
		sPalindrome.add("tom");
		sPalindrome.add("civic");
		sPalindrome.add("radar");
		sPalindrome.add("jimmy");
		sPalindrome.add("kayak");
		sPalindrome.add("john");
		sPalindrome.add("refer");
		sPalindrome.add("billy");
		sPalindrome.add("did");
		
		ArrayList<String> oPalindrome = new ArrayList<>();
		StringBuilder rever = new StringBuilder();
		
		for(int i = 0; i < sPalindrome.size(); i++) {
			
			rever.append(sPalindrome.get(i));
			//System.out.println(rever);
			rever = rever.reverse();
			
			if(sPalindrome.get(i).equals(rever.toString())) {
				oPalindrome.add(rever.toString());
			}
			rever.setLength(0);
		}
		
		for(int i = 0; i < oPalindrome.size(); i++) {
			System.out.println(oPalindrome.get(i));
		}
		
	}

}
