package com.ex.casting;

public class UsefulInfo {
	public static void main(String[] args) {
		/*
		 * Remember that primitives (besides char and boolean) are represented
		 * with signed bits (meaning positive AND negative values)
		 * 
		 * byte		8  bits
		 * short	16 bits
		 * int		32 bits
		 * long		64 bits
		 * 
		 * USEFUL INFO:
		 * # of #'s represented by datatype: Math.pow(2, numBits)
		 * 
		 * Min: (Math.pow(2, numBits) / 2) * -1 
		 * Max: (Math.pow(2, numBits) / 2) - 1
		 */
		System.out.println("byte     :  " + (Math.pow(2, 8)));
		System.out.println("byte Min : " + ((Math.pow(2, 8) /2) * -1));
		System.out.println("byte Max :  " + ((Math.pow(2, 8) /2) - 1));
		System.out.println();
		
		System.out.println("short     :  " + (Math.pow(2, 16)));
		System.out.println("short Min : " + ((Math.pow(2, 16) /2) * -1));
		System.out.println("short Max :  " + ((Math.pow(2, 16) /2) - 1));
		System.out.println();
		
		System.out.println("int     :  " + (Math.pow(2, 32)));
		System.out.println("int Min : " + ((Math.pow(2, 32) /2) * -1));
		System.out.println("int Max :  " + ((Math.pow(2, 32) /2) - 1));
		System.out.println();
		
		System.out.println("long     :  " + (Math.pow(2, 64)));
		System.out.println("long Min : " + ((Math.pow(2, 64) /2) * -1));
		System.out.println("long Max :  " + ((Math.pow(2, 64) /2) - 1));
		System.out.println();
		
	}
}
