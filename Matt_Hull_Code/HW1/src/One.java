
public class One {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//This is Bubble Sort
		int numbers[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i] > numbers[i + 1]) {
					sorted = false;
					int holder = numbers[i];
					numbers[i] = numbers[i + 1];
					numbers[i + 1] = holder;
				}
			}
		}
		for(int i = 0; i < numbers.length;i++) {
			System.out.println(numbers[i]);
		}

	}

}
