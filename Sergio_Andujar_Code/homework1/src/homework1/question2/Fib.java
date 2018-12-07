package homework1.question2;

/**
 * Fibanoci sequence with recursion
 * @author Sergio Andujar
 * @version 1.8
 */

public class Fib {

	public static void main(String[] args) {
		for (int i = 0; i <=25; i++) {
			System.out.println(fibonacci(i));
		}
	}
	
	private static int fibonacci(int number){
			// if the number is 0 or 1, get out of the recursion
			if (number <= 1) return number;
			return fibonacci(number - 1) + fibonacci(number -2);
		}

	

}
