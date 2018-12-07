package two.hwk;

public class QuestionTwo {
	/*
	 * The problem is Question 2:
	 * 
	 * Write a program to display the first 25 Fibonacci(fib) numbers beginning at 0.
	 * 
	 */

	/*
	 * (1) Initializing an array(arr) and setting it to a size of 25; Then I'm
	 * creating the first two fib numbers and adding those to the array.
	 * I call the first two variable(var) which are called first and second. Add
	 * them to their designated area in the array.
	 * 
	 * (2) I am using a for loop to iterate through the size of the array; Then I'm
	 * using the fib formula F_n = F_n-1 + F_n-2 to find the next value in the
	 * sequence(seq); I use abs(absolute) from the Math library to turn any negative
	 * value I get into a positive value; Use those values to point at what is in
	 * that location in the arr. From that point I can then add the values together
	 * to create the next value in the sequence.
	 * 
	 * (3) Using a for each loop to print all the values I saved in the arr. I'm not
	 * using swigly braces because it's condition only one line.
	 * 
	 */

	public static void main(String[] args) {

		int arr[] = new int[25]; // Look at (1)

		int first = 0, second = 1;
		arr[0] = first;
		arr[1] = second;

		for (int h = 2; h < arr.length; h++) { // Look at (2)
			int fib = arr[Math.abs(h - 1)] + arr[Math.abs(h - 2)];
			arr[h] = fib;
		}
		int init = 0;
		System.out.println("The first 25 numbers of Fibonacci sequence:");
		for (int fib : arr) // Look at (3)
			System.out.println("Fib at " + init++ + ": " + fib);

	}

}
