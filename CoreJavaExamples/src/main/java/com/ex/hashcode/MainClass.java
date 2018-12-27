package com.ex.hashcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Try running this main method several times:
 *   - with both hashCode and equals uncommented
 *   - with just hashCode commented out
 *   - with just equals commented out
 *   
 * NOTE: The first case is correct.  The latter two return null.
 *       
 * 
 * There are 3 rules we must follow:
 *   1) When hashCode is invoked many times on the same object, it must always return the same integer, provided no information used in the equals(Object) method has changed.
 *   2) If two objects are equal (according to the equals(Object) method), then they must have the same hashCode.
 *   3) If two objects are NOT equal (according to the equals(Object) method), it is NOT required that they have different hashCodes.
 *   
 * NOTE: In number 3 above, when two unequal objects have the same hashCode, this is called a hash collision.
 *       This decreases performance.
 * 
 * NOTE: Most people forget to follow number 2.
 */
public class MainClass {
	public static void main(String[] args) {
		
		Map<PhoneNumber, String> map = new HashMap<>();
		System.out.println("CALL PUT");
		map.put(new PhoneNumber(123, 456, 7890), "John");
		
		System.out.println("\nCALL GET");
		String john = map.get(new PhoneNumber(123, 456, 7890));
		
		System.out.println("\n" + john);
		
	}
}
