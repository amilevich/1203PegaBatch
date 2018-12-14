package com.examples.exception;

import com.examples.pojo.Person;

public class ExceptionEample {

	
		public static void main(String[] args) {

			Person p = new Person();
			p.setName("Steve");
			int inputAge = 255;
			if(inputAge <= 150 ) {
				p.setAge(inputAge);
			}
			System.out.println(p);
			
			
		}
}
