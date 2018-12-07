package hw.weekone;

import java.util.Scanner;

public class Q3 {
	public static void reverse() {
		Scanner keyboard= new Scanner(System.in);
		System.out.println("pklease enter a string");
		String original=keyboard.nextLine();
		keyboard.close();
		int length=original.length();
		for(int i=0; i<length;i++) {
			original=original.concat(original.substring(length-i-1, length-i));
		}
		System.out.println(original.substring(length));
	}
	public static String reverseInput(String s) {
		int length=s.length();
		for(int i=0; i<length;i++) {
			s=s.concat(s.substring(length-i-1, length-i));
		}
		return s.substring(length);
	}

}
