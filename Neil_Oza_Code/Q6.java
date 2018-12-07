package hw.weekone;

import java.util.Scanner;

public class Q6 {
	public static void isEven() {
		boolean isEven;
		Scanner keyboard= new Scanner(System.in);
		System.out.println("please enter a number");
		int number= keyboard.nextInt();
		int evenChecker=number/2;
		evenChecker=evenChecker*2;
		if(number==evenChecker)
			System.out.println("it's even");
		else
			System.out.println("it's odd");
		
	}
	

}
