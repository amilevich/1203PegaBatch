package com.homework.q18;

public class StrChild extends StrManip {

	@Override
	public boolean checkUpperCase(String str) {
		for(int i = 0; i < str.length(); i++) 
		{
			if(Character.isUpperCase(str.charAt(i)))
				return true;
		}
		
		return false;
	}

	@Override
	public String lowerToUpper(String str) {
		str = str.toUpperCase();
		return str;
	}

	@Override
	public void stringToInt(String str) {
		int sum = 0;
		for(int i = 0; i < str.length(); i++) 
		{
			sum += str.charAt(i);
		}
		
		sum += 10;
		
		System.out.println(sum);
		
	}

}
