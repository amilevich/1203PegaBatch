package com.ex.enums;

public class UsingEnums {
	
	public static final String MONDAY = "MONDAY";
	
	public static void main(String[] args) {
		
//		printDay(MONDAY);
//		printDay("This is not a day. Therefore, not type safe.");
		
		printDayUsingEnum(DaysOfTheWeek.MONDAY);
//		printDayUsingEnum("Compile Error"); //compile error
	}
	
	static void printDay(String day){
		System.out.println(day);
	}
	
	static void printDayUsingEnum(DaysOfTheWeek day){
		System.out.println(day);
	}
	
}
