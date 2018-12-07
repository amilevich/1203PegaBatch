package homework1.question18;

/**
 * abstract class string manipulation 
 * @author Sergio Andujar
 * @version 1.8
 */

public abstract class abstraction {
	
	public abstraction() {};
	public abstract boolean checkUpperCase(String word);
	public abstract String convertToUpper(String word);
	public abstract void convertToInt(String number);

}

class modString extends abstraction{
	
	public boolean checkUpperCase(String word) {
		for(int i = 0; i < word.length(); i++) {
			// using ascii values to see if the char value
			// falls between the ascii values of 'A' to 'Z'
			// if so, we found an upper case letter
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
				return true;
		}
		return false;
	}
	
	public String convertToUpper(String word) {
		String upper = new String("");
		for(int i = 0; i < word.length(); i++) {
			// using ascii values to see if the char value
			// falls between the ascii values of 'a' to 'z'
			// if so, change the letter to upper case equivalent by 
			// subtracting 32 
			if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
				int temp;
				char c = word.charAt(i);
				temp = (int)c - 32;
				c = (char)temp;
				upper += c;
			}
				
		}
		
		return upper;
		
	}
	public void convertToInt(String number) {
		Integer num = new Integer(number);
		System.out.println(num);
		
	}

	
}

class main{
	
	public static void main(String[] arg) {
		String wordOne = "hello";
		String wordTwo = "hey";
		String wordThree = "18";
		modString aString = new modString();
		System.out.println(aString.checkUpperCase(wordOne));
		System.out.println(aString.convertToUpper(wordTwo));
		aString.convertToInt(wordThree);
		
	}
}

