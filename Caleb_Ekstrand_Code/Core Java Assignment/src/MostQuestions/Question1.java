package MostQuestions;
import java.util.Arrays;

public class Question1 {

	public static void main(String[] args) {
		// given array = 1,0,5,6,3,2,3,7,9,8,4
		// use bubble sort to put it in order

		int array[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 }; // the array you need to bubble
		int temp; // a place holder for a value being switched during bubble sort
		boolean changeMade = false; // keeps track whether a change was made during each iteration of the sort

		System.out.println("The original array: " + Arrays.toString(array)); // < I found how to print an array 

		// A do while loop is used here because we always want the program to check
		// whether or not the array is sorted at least once
		// Will run until changeMade reaches the while statement while being false
		do {
			changeMade = false; // refreshes each iteration of the bubble sort
			for (int i = 0; i < array.length - 1; i++) {
				// this is the actual "bubble" step.
				// only occurs if the left element is greater than the right element
				if (array[i] > array[i + 1]) {
					temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					changeMade = true; // Indicates that a change was made!
				}

			}
			System.out.println("Changes made so far: " + Arrays.toString(array));

		} while (changeMade);
		System.out.println("The bubble-sorted array: " + Arrays.toString(array));
	}
}
