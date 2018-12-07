package MostQuestions;
import java.util.ArrayList;

public class Question8 {

	public static void main(String[] args) {
		// takes an array of strings. returns an array of palindromes
		ArrayList<String> arrlist = new ArrayList<String>() {
			//“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”
			{
				add("karan");
				add("madam");
				add("tom");
				add("civic");
				add("radar");
				add("jimmy");
				add("kayak");
				add("john");
				add("refer");
				add("billy");
				add("did");
				add("bacon...mmmm...bacon");
			}
				
		};
		System.out.println(getPalindromes(arrlist));

	}
	public static ArrayList<String> getPalindromes(ArrayList<String> al) {
		//uses isPalindromes to get make an arraylist of only palindromes
		ArrayList<String> paliList = new ArrayList<String>();
		for(String string: al) {
			if (isPalindrome(string)) {
				paliList.add(string);
			}
		}
		return paliList;
		
	}
	public static boolean isPalindrome(String string) {
		//detects for palindromes
		for (int i = 0; i < string.length()/2; i++) {
			if (string.charAt(i) != string.charAt(string.length()-1-i)) {
				return false;
			}
		}
		return true;
	}
}
