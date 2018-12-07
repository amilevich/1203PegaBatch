package homework1.question5;

/**
 * Display a range of characters in a string
 * @author Sergio Andujar
 * @version 1.8
 */


public class subStringBetween {
	
	public static void main(String[] args) {
		String str = new String("Hello Holy Grail!");
		Integer idx = new Integer(6);
		String subString = new String(getSubString(str, idx));
		System.out.println(subString);
		
	}

	private static String getSubString(String str, Integer idx) {
			String subString = new String();
			for(int i = 0; i < idx - 1; i++) {
				subString += str.charAt(i);
			}
			return subString;
	}

}
