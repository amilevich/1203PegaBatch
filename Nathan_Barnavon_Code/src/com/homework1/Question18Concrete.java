package com.homework1;

public class Question18Concrete extends Question18Abstract {

	public static void main(String[] args) {
		
		Question18Abstract q18 = new Question18Concrete();
		
		String testString = "naw";
		String testInteger = "100";
		
		if (q18.checkUppercase(testString)) {
			System.out.println("Contains Uppercase");
		} else {
			System.out.println("no uppercase");
		}
		
		System.out.println(q18.makeUppercase(testString));
		
		System.out.println(q18.parseAndAddTen(testInteger));
		
	}

	@Override
	public boolean checkUppercase(String s) {
		// check each character for the ascii value and if it falls between the
		// 'capital letter range' then the string contains capitals
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) > 64 && s.charAt(i) < 91)
				return true;
		}
		return false;
	}

	@Override
	public String makeUppercase(String s) {
		return s.toUpperCase();
	}

	@Override
	public int parseAndAddTen(String s) {
		Integer i = Integer.parseInt(s);
		return i + 10;
	}

}
