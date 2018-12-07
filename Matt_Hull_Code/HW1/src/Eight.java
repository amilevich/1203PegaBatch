import java.util.ArrayList;
import java.util.Arrays;

public class Eight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] origList = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did"};
		ArrayList<String> newList = new ArrayList<String>( Arrays.asList(origList));
		ArrayList<String> palins = new ArrayList<String>();
		//Comparing first and last chars then next and so on for sameness
		for(String str : newList) {
			boolean isPal = true;
			for(int i = 0; i < str.length() / 2 && isPal; i++) { // ignores middle letter in uneven words
				isPal = isPal && (str.charAt(i) == str.charAt(str.length()-(i+1))); //add 1 to amount subtracted cause index starts at 0
			}
			if (isPal){
				palins.add(str);
			}
		}
		System.out.println(palins);
		
	}

}
