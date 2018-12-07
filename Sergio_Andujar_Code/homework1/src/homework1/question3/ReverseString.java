package homework1.question3;

/**
 * reverse string
 * @author Sergio Andujar
 * @version 1.8
 */

public class ReverseString {
	
	public static void main(String[] args) {
		
		String aString = new String("Hello World!");
		String reversedString = ""; 
		
		// iterate through the string in reverse 
		for(int i = aString.length(); i != 0; i--) {
			reversedString += aString.charAt(i-1);
		}
		System.out.println(reversedString);
	}

}
