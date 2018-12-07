package MostQuestions;

public class Question10 {

	public static void main(String[] args) {
		// Find the minimum of 2 ints
		int a = 123;
		int b = 231;
		int c = 444;
		int d = 1;
		System.out.println(findMin(a, b));
		System.out.println(findMin(a, c));
		System.out.println(findMin(a, d));
		System.out.println(findMin(b, c));
		System.out.println(findMin(b, d));
		System.out.println(findMin(c, d));

	}

	public static int findMin(int one, int two) {
		// if one is less than two return one, else return two
		int min = one < two ? one : two;
		return min;

	}

}
