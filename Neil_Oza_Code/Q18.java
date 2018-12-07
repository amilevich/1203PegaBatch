package hw.weekone;

import java.util.Scanner;

public class Q18 extends Q18Super{

	public boolean checkForUpperCase() {
		Scanner keyboard= new Scanner(System.in);
		String input = keyboard.nextLine();
		keyboard.close();
		String duplicate=input.toLowerCase();
		return(!input.equals(duplicate));
		
	}
	public  String capitalize() {
		Scanner keyboard= new Scanner(System.in);
		String input = keyboard.nextLine();
		keyboard.close();
		String result= input.toUpperCase();
		return result;
		
	}
	public  int add10() {
		Scanner keyboard= new Scanner(System.in);
		String input = keyboard.nextLine();
		keyboard.close();
		
		int add10=Integer.parseInt(input, 0);
		add10=add10+10;
		return add10;
				
	}

}
