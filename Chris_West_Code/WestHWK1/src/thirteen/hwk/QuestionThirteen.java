package thirteen.hwk;

public class QuestionThirteen {
	/*
	 * The problem is Question 13:
	 * 
	 * Display the triangle on the console as follows using any type of loop. Do NOT
	 * use a simple group of print statements to accomplish this. 0 10 101 0101
	 * 
	 */

	/*
	 * (1) So, I start with a Boolean isZero that is true; It makes sense to me
	 * cause the first value I wanted printed to be a zero.
	 * 
	 * (2) The for-loop ending index is outside the loop because I need it to go
	 * through several iterations. Also, because I only need it to print a specific
	 * amount of numbers for each line. I use a ternary operator because I'm not
	 * doing that much coding that requires a normal if-else statement. The reason I
	 * have the Boolean isZero = !isZero after the print statement is to change
	 * isZero back and forth to true and false. Simply, so that when it goes through
	 * the ternary operator if its true it will always print the first statement
	 * while false will pick the second option.
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int x = 0;
		Boolean isZero = true; // Look at (1)
		do {// Look at (2)
			for (int s = 0; s <= x; s++) {
				System.out.print(isZero ? "0 " : "1 ");
				isZero = !isZero;
			}
			System.out.println();
			x++;
		} while (x < 4);
	}

}
