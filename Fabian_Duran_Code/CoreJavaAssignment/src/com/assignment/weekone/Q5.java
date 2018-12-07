package com.assignment.weekone;

public class Q5 {
	static String subStringthis (String s, int n) {
		String b = "";//temp variable
		for (int i = 0; i<n;i++) {
			if (n>s.length()) {//just in case int input is bigger than word
				System.out.println("Please use appropriate number to word used");
				return s;
			}
				
			b=b+s.charAt(i);//using charAt to add characters up to specified length
			System.out.println(b);//just to test
		}
		return b;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word = "String";//word
		String word2= subStringthis(word,3);//using substring method 
		System.out.println(word+" "+word2);//the proof
		
	}

}
