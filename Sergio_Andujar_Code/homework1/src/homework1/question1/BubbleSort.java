package homework1.question1;

/**
 * Bubble sort
 * @author Sergio Andujar
 * @version 1.8
 */

public class BubbleSort {

	public static void main(String[] args) {
		int[] numbers = {1,0,5,6,3,2,3,7,9,8,4};
		display(numbers);
		int[] sortedNumbers = bubbleSort(numbers);
		display(sortedNumbers);

	}
	
	private static void display(int[] sortedNumbers) {
		for(int num : sortedNumbers) {
			System.out.print(num + " ");
		}
		System.out.print("\n");
		
	}

	public static int[] bubbleSort(int[] numbers) {
		// temp variable for potential swapping
		int temp = 0;
		for(int i = 0; i < numbers.length; i++) {
			for(int j = 1; j < (numbers.length - i); j++) {
				// if the value at this element is greater
				// than the value at the element a head of it
				// it will swap the two values
				if(numbers[j-1] > numbers[j]){
					temp = numbers[j-1];
					numbers[j-1] = numbers[j];
					numbers[j] = temp;
				}
			}
		}
		return numbers;
	}

}
