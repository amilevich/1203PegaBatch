package com.assignment.weekone;

public class Q18SubClass extends Q18AbstractClass {
	
	//will be used in following methods
	
	public Q18SubClass(String tester) {
		this.tester=tester;//stores method object into class object, inherited from parent class
	}
	
	@Override
	public boolean isUpperCase() {
		String stringHolder = tester.toLowerCase();//converts inputed string into lower case
		if (stringHolder.equals(tester))//if no upper cases, this true
			return false;//returns false because string has no upper cases
		else
			return true;//will return true if there is an uppercase
	}

	@Override
	public String capitalizeString() {
		
		return tester.toUpperCase();
	}

	@Override
	public int convertToInt() {
		int num = 10;//since a string searched to add an integer to the string
		for (int i = 0; i<tester.length();i++)
			num = num + (int)tester.charAt(i);//cast to int character by character
		return num;
	}

}
