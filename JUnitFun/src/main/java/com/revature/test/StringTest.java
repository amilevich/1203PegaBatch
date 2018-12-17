package com.revature.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
//Absence of failure is success!
class StringTest {
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before All");
	}
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Intialize Test Data for "
				+ info.getDisplayName());
	}
	//After 
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Cleaning up Test Data for " + 
		 info.getDisplayName());
	}

	@Test //method that forms a JUnit Test
	void meh() {
		//testing .length()
		int actualLength= "ABCD".length();
		int expectedLength = 4;
		//asserts!
		//assertEquals(expected, actual value)
		assertEquals(expectedLength,actualLength);
		
	}
	@Test
	void toUpperCase() {
		String s= "abcd";
		String result = s.toUpperCase();
		assertEquals("ABCD",result);
		}
	@Test
	void containsBasics() {
		String s = "abcdefg";
		boolean result=s.contains("ijk");
		//assertEquals (false,result);
		assertFalse(result);
		//Inline
		//assertFalse("abcdefg".contains("ijk"));
	}
	@Test
	@DisplayName("Check for Exceptions Please")
	//test for exceptions
	void length_exception() {
		String s = null;
		assertThrows(NullPointerException.class,
				()->{
					s.length();
				});
	}
}
