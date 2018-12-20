package com.example.javaquestions;
import java.util.ArrayList;

public class Q8 {
	// Q8. Write a program that stores the following strings in an ArrayList and
	// saves all the palindromes in another ArrayList.
	// “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”, “refer”,
	// “billy”, “did”
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<String> palindromes = new ArrayList<String>();

		strings.add("karan");
		strings.add("madam");
		strings.add("tom");
		strings.add("civic");
		strings.add("radar");
		strings.add("jimmy");
		strings.add("kayak");
		strings.add("john");
		strings.add("refer");
		strings.add("billy");
		strings.add("did");

		for (int i = 0; i < strings.size(); i++) {
			// System.out.println(strings.get(i));
			StringBuilder reverseString = new StringBuilder();
			reverseString.append(strings.get(i));

			if (reverseString.reverse().toString().contains(strings.get(i))) {
				palindromes.add(strings.get(i));
			}
		}

		printStrings(strings);
		printPalindromes(palindromes);

	}

	private static void printPalindromes(ArrayList<String> palindromes) {
		System.out.println("\nPALINDROMES:");
		for (int i = 0; i < palindromes.size(); i++) {
System.out.println(palindromes.get(i));
		}
	}

	private static void printStrings(ArrayList<String> strings) {
		System.out.println("\nSTTRINGS:");
		for (int i = 0; i < strings.size(); i++) {
			System.out.println(strings.get(i));
		}
	}

}
