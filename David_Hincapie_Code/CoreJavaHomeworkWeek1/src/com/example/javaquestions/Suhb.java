package com.example.javaquestions;

public class Suhb extends Supa {

	@Override
	public boolean haveUpper(String s) {
		char c;

		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isUpperCase(c)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String makeUpper(String s) {
		return s.toUpperCase();
	}

	@Override
	public int makeInt(String s) {
		int num;
		num = Integer.parseInt(s);
		num += 10;
		return num;
	}

}
