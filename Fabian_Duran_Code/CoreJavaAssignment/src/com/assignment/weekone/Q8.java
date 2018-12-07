package com.assignment.weekone;

import java.util.ArrayList;

public class Q8 {
	public static boolean isPalindrome(String S) {
		StringBuilder input = new StringBuilder();//using stringbuilder because it has  method reverse
		input.append(S);//add string to stringbuilder object
		input.reverse();//reverse it
		String P = input.toString();//back to string
		if (S.equals(P))//makes the comparison to original string
			return true;
		else
			return false;		
	}
	public static void main(String[] args) {
		ArrayList <String> list = new ArrayList<String>();//original list
		ArrayList <String> palindrome = new ArrayList<String>();//palindrome list
		list.add(new String("karan"));//populate list
		list.add(new String("madam"));
		list.add(new String("tom"));
		list.add(new String("civi"));
		list.add(new String("radar"));
		list.add(new String("jimmy"));
		list.add(new String("kayak"));
		list.add(new String("john"));
		list.add(new String("refer"));
		list.add(new String("did"));
		for (int i = 0; i< list.size();i++) {//filter palindromes only
			if (isPalindrome(list.get(i))) {
				palindrome.add(list.get(i));
			}
		}
		System.out.println("Original:");//work displayed
		for (int i = 0; i<list.size();i++)
			System.out.print(list.get(i)+", ");
		System.out.println("\nPalindromes Only:");
		for (int i = 0; i<palindrome.size();i++)
			System.out.print(palindrome.get(i)+", ");
	}

}
