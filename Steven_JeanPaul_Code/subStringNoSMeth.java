
public class subStringNoSMeth {
//Author: Steven Jean-Paul
//Q5 - Substring Method
	public static void main(String[] args) {
		subOfString("freakazoid", 60); //Uses static method to create a substring.
		
	}

	public static String subOfString(String str, int idx) {
		
		String sub = str;
		idx = str.length() - 1;
		
		for(int i = 0; i < idx; i++) {
			if (i < idx) {
				System.out.println(sub.charAt(i));
			}
		}
		return sub;
	}

}
