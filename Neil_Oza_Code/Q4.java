package hw.weekone;

import java.util.Scanner;

public class Q4 {
	public static void factorial() {
		Scanner keyboard=new Scanner(System.in);
		System.out.println("please type a number");
		int n=keyboard.nextInt();
		keyboard.close();
		int nFactorial=1;
		for (int i=n; i>1; i--) {
			nFactorial=nFactorial*i;
		}
		System.out.println(nFactorial);
	}

}
