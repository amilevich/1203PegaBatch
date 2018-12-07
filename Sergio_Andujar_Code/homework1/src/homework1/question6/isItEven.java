package homework1.question6;

/**
 * boolean even program
 * @author Sergio Andujar
 * @version 1.8
 */

public class isItEven {

	public static void main(String[] args) {
		for(int i =0; i < 11; i++) {
			System.out.println(isEvenOrNot(i));
		}

	}

	public static boolean isEvenOrNot(int number) {
		
		boolean isEven = true;
		int checking = number / 2;
		
		//if the checking * 2 yields it's original even value, it's true
		if (checking * 2 == number) {
			return isEven;
		}
		isEven = false;
		return isEven;
	}

}
