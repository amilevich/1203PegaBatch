package hw.weekone;

public class Q1 {

	public int[] bubbleSort() {
		int[] array1 = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i]);
		}
		System.out.println();
		int swapsMade = 0;
		do {
			swapsMade = 0;
			for (int i = 1; i < array1.length; i++) {

				if (array1[i - 1] > array1[i]) {
					int temp = array1[i];
					array1[i] = array1[i - 1];
					array1[i - 1] = temp;
					swapsMade++;
					
				}
				for (int j = 0; j < array1.length; j++) {
					System.out.print(array1[j]);
				}
				System.out.println();

			}
			for (int i = 0; i < array1.length; i++) {
				System.out.print(array1[i]);
			}
			System.out.println();
		} while (swapsMade != 0);

		return array1;

	}

}
