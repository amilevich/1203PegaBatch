package MostQuestions;

public class Question12 {

	public static void main(String[] args) {
		printEvens(getArray(100));

	}

	public static int[] getArray(int size) {
		// builds an array of size with values of 1 to size
		int array[] = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1;
			// System.out.println(array[i]);
		}
		return array;
	}

	public static void printEvens(int[] array) {
		// prints all even nums in an array
		for (int i : array) {
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}
	}

}
