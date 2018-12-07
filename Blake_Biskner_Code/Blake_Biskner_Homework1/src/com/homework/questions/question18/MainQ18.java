package com.homework.questions.question18;

/**
 * Driver class for Question 18
 * @author Blake Biskner
 * @version 1.18
 */

public class MainQ18{
	
	public static void main(String[] args) {
		// Variable Initialization
		String uppercase="testString";
		String lowercase="TEST_STRING";
		String intString="10";
		// Object Instantiation
		StringChangerQ18 s1=new StringChangerQ18(uppercase);
		StringChangerQ18 s2=new StringChangerQ18(lowercase);
		StringChangerQ18 s3=new StringChangerQ18(intString);
		// Object Method Calls
		System.out.println("String Method Results");
		System.out.println(uppercase+" has an uppercase character= "+s1.isUppercase());
		System.out.println(lowercase+" in all lowercase= "+s2.toLowercase());
		System.out.print(intString+" using the addToInt() method prints= ");
		s3.addToInt();
	}
}
