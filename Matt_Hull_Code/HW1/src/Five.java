
public class Five {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "String";
		int idx = 3;
		String subStr = "";
		//Be building that sub string 1 char at a time
		for(int i = 0; i < idx; i++) {
			subStr += str.charAt(i);
		}
		System.out.println(subStr);
	}

}
