package com.homework.q5;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Gotcha!";
		String sub;
		int idx = 1;
		
		sub = subString(str, idx);
		
		System.out.println(sub);

	}

	private static String subString(String str, int idx) {
		// TODO Auto-generated method stub
		String sub = "";
		for(int i = 0; i < idx; i++) {
		
			sub += str.charAt(i); 
		}
		
		return sub;
	}

}
