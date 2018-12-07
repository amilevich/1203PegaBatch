package homework1.question4;

/**
 * Display Factorial 
 * @author Sergio Andujar
 * @version 1.8
 */

public class Factorial {

	public static void main(String[] args) {
		int n = 9;
		int factorial = n; 
		
		for (int i = n-1; i != 0; i--) {
			factorial *= i;
		}
		System.out.println(factorial);
	}

}
