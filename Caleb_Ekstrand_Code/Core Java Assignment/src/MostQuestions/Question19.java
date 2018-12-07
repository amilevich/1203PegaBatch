package MostQuestions;
import java.util.ArrayList;

public class Question19 {

	public static void main(String[] args) {
		
		ArrayList<Integer> al = new ArrayList<>(); //make an arraylist with values 1-10
		for (int i = 1; i < 11; i++) {
			al.add(i);
		}
		
		System.out.println(al); //print it out
		int evens = 0;
		int odds = 0;
		for (int i : al) {
			if (i % 2 == 0) {
				evens += i; //add up evens
			} else {
				odds += i; //ad up odds
			}

		}
		//print both out
		System.out.println("Sum of evens: " + evens);
		System.out.println("Sum of odds: " + odds);
		// System.out.println(al);
		
		for (int n = 0; n < al.size(); n++) {
			// System.out.println("n: " + n);
			// System.out.println("al[n]; " + al.get(n));
			if (Question9.isPrime(al.get(n))) { // use the isPrime method from question 9 to determine if it is a prime, remove it if so
				// System.out.println("Removing: " + al.get(n));
				al.remove(n);
				n--; // compensate for the removed element
			}
		}
		System.out.println(al); // print out all non-primes
	}

}
