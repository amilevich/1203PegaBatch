package com.projectz.bankapp;

public class accountGenerator {
	//private double ranAcc = 0;
	
	public static int accGen() {
		double ran = Math.random();
		double rann = ran * 100000;
		Double rannn = (Double)Math.ceil(rann);
		int ranAcc = rannn.intValue();
		return ranAcc;
	}
}
