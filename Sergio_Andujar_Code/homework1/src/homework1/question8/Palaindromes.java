package homework1.question8;

import java.util.ArrayList;

/**
 * Palaindrome
 * @author Sergio Andujar
 * @version 1.8
 */

public class Palaindromes {
	
	public static void main(String[] args) {
		
		ArrayList<String> names = new ArrayList<String>(); 
		ArrayList<String> palaindromes = new ArrayList<String>();
        
		names.add("Karan");
        names.add("madam");
        names.add("tom");
        names.add("civic");
        names.add("radar");
        names.add("jimmy");
        names.add("Kayak");
        names.add("john");
        names.add("refer");
        names.add("billy");
        names.add("did");
        
        for (String name : names) {
        	// compare the two strings
    		// if the returned string is the same as the compared string
        	// add it to the palaindromes arrayList
        	if(name.equals(reverseTheString(name))){
        		palaindromes.add(name);
        	}
        }
        

        for(String name: palaindromes) {
        	System.out.println(name);
        }

	}

	private static String reverseTheString(String name) {
		
		String reversedString = "";
		
		for(int i = name.length(); i != 0; i--) {
			reversedString += name.charAt(i-1);
		}

		return reversedString;
	}

}
