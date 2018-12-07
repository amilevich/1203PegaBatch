package three.hwk;

public class QuestionThree {
	/*
	 * The problem is Question 3:
	 * 
	 * Reverse a string without using a temporary variable. Do NOT use reverse() in
	 * the StringBuffer or the StringBuilder APIs.
	 * 
	 */

	/*
	 * (1) The reverse method is taking a String data type with the id of s. If the
	 * s length is zero then it will return s and its return will be empty.
	 * 
	 * (2) So, here we are recursively recalling the method over and over until the
	 * substring length gets to zero. When we submit the string back to the method
	 * were taking everything from index 1 to the end of the index. Then outside the
	 * calling of the method were adding what was at the location of zero from the
	 * string. Because once it gets to length zero and returns its going to start
	 * adding it back. The first one will return empty and it will be added to the
	 * previous value that was at zero. So, "" + s.char(0) = s -> s + s.char(0) = sd
	 * -> until it returns back to all the methods that were called.
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		String s = "words";
		System.out.println(reverse(s));

	}

	public static String reverse(String s) {
		if (s.length() == 0) { // Look at (1)
			System.out.println(s);
			return s;
		} else
			return (reverse(s.substring(1)) + s.charAt(0)); // Look at (2)
	}

}
