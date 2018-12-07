package hw.weekone;

import java.util.ArrayList;

public class Q9 {
	public static void printPrimes() {
		ArrayList<Integer> arrayL= new ArrayList<Integer>(100);
		for(int i=1;i<=100;i++) {
			
			arrayL.add(i);
		}
		arrayL.remove(0);
		ArrayList<Integer> primes= new ArrayList<Integer>();
		for(int x : arrayL) {
			boolean composite=false;
			for(int i=2; i<x;i++) {
				if (x%i==0) {
					composite=true;
				}
			}
			if (!composite) {
				primes.add(x);
			}
		}
		for(int x: primes) {
			System.out.println(x);
		}
	}

}
