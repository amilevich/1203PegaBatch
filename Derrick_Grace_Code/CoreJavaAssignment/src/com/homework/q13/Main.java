package com.homework.q13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("Triangle.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String read;
			while((read = br.readLine()) != null)  {
				System.out.println(read);
			}
		}catch (IOException e) {
			
		}
	}

}
