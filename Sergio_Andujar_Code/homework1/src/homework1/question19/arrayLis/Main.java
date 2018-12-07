package homework1.question19.arrayLis;
import homework1.question6.isItEven;
import homework1.question9.Prime;
import java.util.ArrayList;

/**
 * display a list of numbers in multiple ways
 * @author Sergio Andujar
 * @version 1.8
 */

public class Main {
	
	public static void main(String[] arg) {
		ArrayList<Integer> numbers = fillList();
		displayList(numbers);
		displayEven(numbers);
		displayOdd(numbers);
		displayNonPrime(numbers);
	}

	private static void displayNonPrime(ArrayList<Integer> numbers) {
		Prime check = new Prime();
		for (int i = 0; i < numbers.size(); i++) {
			if(check.isPrime(i)) {
				numbers.remove(i);
			}
		}
		System.out.println("Non prime numbers: ");
		displayList(numbers);
		
	}

	private static void displayEven(ArrayList<Integer> numbers) {
		isItEven check = new isItEven();
		int evenSum = 0;
		for(Integer num : numbers) {
			if(check.isEvenOrNot(num)) {
				evenSum += num;
			}
		}
		System.out.println("Even sum: " + evenSum);
	}
	
	private static void displayOdd(ArrayList<Integer> numbers) {
		isItEven check = new isItEven();
		int oddSum = 0;
		for(Integer num : numbers) {
			if(!(check.isEvenOrNot(num))) {
				oddSum += num;
			}
		}
		System.out.println("Odd sum: " + oddSum);
	}

	private static ArrayList<Integer> fillList() {
		// TODO Auto-generated method stub
		ArrayList<Integer> filledNumbers = new ArrayList();
		for (int i = 1; i < 11; i++) {
			filledNumbers.add(i);
		}
		return filledNumbers;
	}
	
	private static void displayList(ArrayList<Integer> numbers) {
		for(Integer num : numbers) {
			System.out.println(num);
		}
	}
}
