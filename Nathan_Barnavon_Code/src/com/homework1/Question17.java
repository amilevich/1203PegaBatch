package com.homework1;

import java.util.Scanner;

public class Question17 {
	
	public static double findInterest(double principal, double rate, double time) {
		return principal*rate*time;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		double principal = sc.nextDouble();
		double rate = sc.nextDouble();
		double time = sc.nextDouble();
		System.out.println(findInterest(principal, rate, time));
		sc.close();
	}
	
	
}
