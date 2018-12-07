package hw.weekone;

import java.util.ArrayList;

public class Q8 {
	public static void palendrome() {
		ArrayList<String> original= new ArrayList<String>();
		ArrayList<String> palendrome= new ArrayList<String>();
		original.add("karan");
		original.add("madam");
		original.add("tom");
		original.add("civic");
		original.add("radar");
		original.add("jimmy");
		original.add("kayak");
		original.add("john");
		original.add("refer");
		original.add("billy");
		original.add("did");
		for(String s: original) {
			if(s.equals(Q3.reverseInput(s))){
				palendrome.add(s);
			}
			
		}
		for(String s: palendrome) {
			System.out.println(s);
		}


		

	}

}
