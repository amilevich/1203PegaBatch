package com.example.threads;

public class Main {

	public static void main(String[] args) {

		Thread worker = new MyThread();
		Thread manager = new Thread(new MyRunnable()); 
		// two ways to create threads
		
		
		manager.start();
		worker.start();
		//worker.run();
		// run vs start? 
		// run method will not create a separate thread
		
		for(int i=0; i<20; i++) {
			System.out.println(Thread.currentThread().getName());
			
		}
	}

}
