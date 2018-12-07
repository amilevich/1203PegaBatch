
public class Three {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "String";
		String newString = "";
		
		//Build the reversed string by getting each character from the end to the beginning
		//Bad for memory, if only were some sort of class to help with building string like this
		for(int i = 1; i <= str.length();i++) {
			newString = newString + str.charAt(str.length()-i);
		}
		System.out.println(newString);
	}

}
