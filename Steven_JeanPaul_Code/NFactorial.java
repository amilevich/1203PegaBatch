
public class NFactorial {

	public static void main(String[] args) {
		factorial(10); //Uses a static method to demonstrate recursion.

	}

	public static int factorial(int n) {
		if(n == 1) {
			//System.out.println(n);
			return 1;
		}
		n = n * factorial(n-1);
		System.out.println(n);
		return n;
	}
}
