package MostQuestions;
import java.util.Arrays;

public class Question2 {

	public static void main(String[] args) {
		// Displays the first 25 fibonacci numbers
		int array[] = new int[25]; // an array to hold the sequence
		for (int i = 0; i < array.length; i++) {
			// the first two numbers of the fibonacci sequence must be given in order for it
			// to work
			if (i == 0) {
				array[i] = 0;
			} else if (i == 1) {
				array[i] = 1;
			} else {
				array[i] = array[i - 1] + array[i - 2]; // n + (n+1) = (n+2) is the pattern the fibonacci sequence
														// follows after the first two elements in the sequence
			}
		}
		System.out.println("The first 25 numbers in the fibonacci sequence are: " + Arrays.toString(array));

	}

}
