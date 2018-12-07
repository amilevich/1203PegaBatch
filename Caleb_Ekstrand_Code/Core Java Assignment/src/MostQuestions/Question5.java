package MostQuestions;

public class Question5 {

	public static void main(String[] args) {
		String string1 = "This be a string";
		int idx1 = 4;
		System.out.println(subString(string1, idx1));
	}

	public static String subString(String string, int idx) {
		// returns a string that is stringindex(0,idx-1)
		String subString = "";
		for (int i = 0; i < idx; i++) {
			subString += Character.toString(string.charAt(i));
		}
		return subString;
	}

}
