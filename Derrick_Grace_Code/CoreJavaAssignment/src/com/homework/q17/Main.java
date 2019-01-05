package com.homework.q17;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		double principle = 0.0;
		double rate = 0.0;
		double time = 0.0;
		double interest = 0.0;
		
		System.out.print("Enter principle: ");
		principle = scan.nextDouble();
		System.out.print("Enter rate: ");
		rate = scan.nextDouble();	
		System.out.print("Enter time: ");
		time = scan.nextDouble();	
		scan.close();
		
		interest = principle * rate * time;
		System.out.println("Interest: " + String.format("%.2f", interest));
		
	}

}
