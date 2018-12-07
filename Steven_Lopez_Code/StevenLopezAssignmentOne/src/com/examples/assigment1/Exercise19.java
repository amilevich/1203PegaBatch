package com.examples.assigment1;

import java.util.ArrayList;

public class Exercise19 {
	// Q19. Create an ArrayList and insert integers 1 through 10. Display the
	// ArrayList. Add all the even numbers up and display the result. Add all the
	// odd numbers up and display the result. Remove the prime numbers from the
	// ArrayList and print out the remaining ArrayList.

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		int sum = 0;
		int sum2 = 0;
		int max = 20;
		
		//Populate the ArrayList
		for (int x = 1; x <= max; x++) {
			al.add(x);
		}

		//Find total sum even and total sum of uneven numbers, then print them
		System.out.println("ArrayList: " + al);
		for (Integer i : al) {
			if ((i / 2) * 2 == i) {
				sum += i;
			} else {
				sum2 += i;
			}
		}

		System.out.println("The sum of all even numbers is: " + sum);
		System.out.println("The sum of all uneven numbers is: " + sum2);

		//Mark all prime numbers to erase later by turning them into negative(*-1)
		for (int i = 2; i * i < al.size(); i++) {
			if (al.get(i - 1) >= 0) {
				for (int d = i * i; d <= al.size(); d += i) {
					al.set(d - 1, al.get(d - 1) * -1);
				}
			}
		}
		
		// ArrayList's removeIf(filter) method passing it 
		// lamda Expression to check if any elements is greater than 0
		// if so remove them from the ArrayList
		al.removeIf(el -> el >= 0); 

		
		//Printing arrayList with prime numbers
		for (int i = 0; i < al.size(); i++) {
			al.set(i, al.get(i) * -1);
		}
		System.out.println("ArrayList after deletion of prime numbers: " + al);
	}

}
