package com.assignment.weekone;

public class Q13 {
	public static void main(String[] args) {
		int ticker = 0;//represents the count
		for (int i = 1; i < 5;i++) {//nest for loop to give the triangle look, 5 and 1 is used so i > j, can be altered to make triangle continue indefinitely
			for(int j= 0; j < i; j++) {
				 System.out.print(ticker+" ");//prints out ticker
				 if(ticker == 0)//here ticker switches between 0 and 1
					 ++ticker;
				 else
					 --ticker;
			}
			System.out.println();//iterates the new line
		}
		
	}
}
