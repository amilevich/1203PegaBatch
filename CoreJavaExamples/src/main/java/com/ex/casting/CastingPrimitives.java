package com.ex.casting;

/*
 * See UsefulInfo class
 */
public class CastingPrimitives {
	public static void main(String[] args) {

		byte  byteVar  = 1; //8  bits (-128 to 127)
		short shortVar = 129; //16 bits (-32,768 to 32,767)
		int   intVar   = 32768; //32 bits
		long  longVar  = (long) 2.247483647E9; //64 bits
		
		/*
		 * Implicit/Up casting
		 * 
		 * NOTE: DO NOT have to specify cast because it will always work
		 */
		short upcast1 = byteVar;
		int   upcast2 = shortVar;
		long  upcast3 = intVar;
		
		System.out.println("upcast1: " + upcast1);
		System.out.println("upcast2: " + upcast2);
		System.out.println("upcast3: " + upcast3);
		
		/*
		 * Explicit/Down casting
		 * 
		 * NOTE: MUST specify cast because it may not work
		 * 		 or it may cause unfortunate results
		 */
		
		byte  downcast1 = (byte)shortVar;
		short downcast2 = (short)intVar;
		int   downcast3 = (int)longVar;
		
		System.out.println("downcast1: " + downcast1); //wrong
		System.out.println("downcast2: " + downcast2); //wrong
		System.out.println("downcast3: " + downcast3); //wrong
		
		
	}
}
