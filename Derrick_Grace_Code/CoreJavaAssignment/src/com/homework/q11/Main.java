package com.homework.q11;

import com.homework.q11helper.Helper;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Helper helper = new Helper(1.0f, 3.3f);
		
		System.out.println(helper.getN1());
		System.out.println(helper.getN2());
		
		helper.setN1(4.5f);
		helper.setN2(34.9f);
		
		System.out.println(helper.getN1());
		System.out.println(helper.getN2());

	}

}
