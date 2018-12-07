package MostQuestions;

public class Question4 {

	public static void main(String[] args) {
		// Finds the nth factorial
		// n * n-1 * ... * 1
		int n = 5; // set to whatever nth factorial you want
		// starts at n-1 and goes until the last element has been multiplied in. 
		for (int i = n-1; i > 0; i--) {
			n *= i;
		}
		System.out.println(n);

	}

}
