package hw.weekone;

import java.util.Scanner;

public class Q17 {
	public static void simpleInterest() {
		Scanner keyboard= new Scanner(System.in);
		System.out.println("please enter the principal mount");
		double principal=keyboard.nextDouble();
		System.out.println("please enter the rate of interest per time period");
		double rate=keyboard.nextDouble();
		System.out.println("please enter how many time periods have elapsed");
		double time=keyboard.nextDouble();
		System.out.println(principal*rate*time);

	}

}
