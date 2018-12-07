package MostQuestions;

public class Question3 {
	public static void main(String[] args) {
		String string = "This is a string";
		// The for loop only runs for half the length on the string as each iteration
		// swaps 2 elements.
		for (int i = 0; i < string.length() / 2; i++) {
			if (i == 0) {
				// this is the concatenation of the last character, all the middle characters,
				// and the first character
				string = Character.toString(string.charAt(string.length() - 1 - i))
						+ string.substring(i + 1, string.length() - 1 - i) + Character.toString(string.charAt(i));
			} else {
				// this switches the nth character with the length-nth character while keeping
				// all other characters in place.
				string = string.substring(0, i) + Character.toString(string.charAt(string.length() - 1 - i))
						+ string.substring(i + 1, string.length() - 1 - i) + Character.toString(string.charAt(i))
						+ string.substring(string.length() - i, string.length());
			}
			// uncomment the next line if you want to see the transformation happen!
			// System.out.println(string);
		}
		System.out.println("Your reversed string is: " + string);

	}
}
