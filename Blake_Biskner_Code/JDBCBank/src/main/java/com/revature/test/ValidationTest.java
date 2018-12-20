package com.revature.test;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.revature.util.InputValidation;

/**
 * JUnit for Input Validation Helper Methods
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

class ValidationTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Input Validation Method Tests");
	}
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Data for "+info.getDisplayName());
	}
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Cleaning Up Data from "+info.getDisplayName());
	}
	@Test
	@DisplayName("Test of dollarTest Method")
	void intTest_test() {
		String amt="12.50";
		boolean result=InputValidation.dollarTest(amt);
		assertTrue(result);
	}
}
