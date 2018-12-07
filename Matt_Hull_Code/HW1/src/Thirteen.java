
public class Thirteen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String zero = "0";
		String one = "1";
		String line = zero;
		//Pattern I followed was: Start with '0', then alternate between adding a character to the right and the left
		//and to make sure that you don't ever have the same character twice in a row
		//IE pattern will always be 0101010101010, adding a number to the string on alternating sides
		// no 0s next to 0s and no 1s next to 1s
		for(int i = 1; i <= 4; i++) {
			System.out.println(line);
			if(i % 2 == 1) {
				if(line.charAt(0)=='0') {
					line = one + line;
				}
				else {
					line = zero + line;
				}
			}
			else {
				if(line.charAt(line.length()-1)=='0') {
					line += one;
				}
				else {
					line += zero;
				}
			}
		}

	}

}
