package one.hwk;

public class QuestionOne {
	/*
	 * The problem is Question One:
	 * 
	 * Perform a bubble sort on the following integer array: 1,0,5,6,3,2,3,7,9,8,4
	 * 
	 */

	/*
	 * (1) So, I start at the value of 1 to not throw my array out of bounds. Then I
	 * subtract by 1 in the arr's index and compare it at the location of the same
	 * arr different index to see if it's greater. If the one that's subtracted by
	 * one is greater than the other one I swap it out. I use a temp value to store
	 * the value that's greater. Move the value that's less to the greater values
	 * location and move the temp value to the location that was previously held by
	 * the value that was less.
	 * 
	 * 
	 * 
	 * Changes: First time I wrote the code I counted every time a swap occurred with
	 * a second if statement checking for the count size is equal to zero and it
	 * being the end of the array(arr). If no swaps occurred it would exit the while
	 * loop. While overlooking the code I realized I didn't need the count, didn't
	 * need to initialize the isSwapped, and I could use a do-while. Why use a
	 * do-while here: I don't need the while loop to check for the boolean when it
	 * hasn't even gone though the for loop yet. But once it's complete if no swaps
	 * occurred then it will exit. Then go print the array using the for-each
	 */
	public static void main(String[] args) {
		Integer arr[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		boolean isSwapped;

		do {
			isSwapped = false;
			for (int c = 1; c < arr.length; c++) {
				if (arr[c - 1] > arr[c]) { // Look at (1)
					int temp = arr[c - 1];
					arr[c - 1] = arr[c];
					arr[c] = temp;
					isSwapped = true;
				}
			}
		} while (isSwapped);

		for (Integer x : arr) { 
			System.out.println(x);
		}
	}

}