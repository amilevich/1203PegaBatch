package homework1.question10;

/**
 * ternary operator
 * @author Sergio Andujar
 * @version 1.8
 */

public class Toperators {
	
	public static void main(String[] args) {
		int num0 = 35;
		int num1 = 30;
		
		// () condition before the ?
		// ? means if I think
		// : means return
		// will determine which of the numbers is the minimum between the two
		int min = (num0 < num1) ? num0 : num1;
		System.out.println(min);
	}

}
