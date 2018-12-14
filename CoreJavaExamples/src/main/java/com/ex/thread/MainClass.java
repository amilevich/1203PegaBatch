package com.ex.thread;

/**
 * Simple Thread Example
 * 
 * Notice the output changes when run multiple times.
 * You do not have control over when a thread runs.
 * The JVM's Thread Scheduler decides who gets to run.
 * This means, you're code cannot depend on a certain scheduling of threads.
 * 
 * 
 * @author TJ
 *
 */
public class MainClass {
	public static void main(String[] args) {

		//Second way
//		Thread worker = new MoreSpecificThread();
//		worker.start();
		

		
		
//		//First way of creating and starting a thread
		Runnable job = new MyRunnable(); //job
		Thread worker = new Thread(job); //worker
		
//		job.run();
		worker.start();
		
		for(int i = 0; i < 20; i++){
			System.out.println(Thread.currentThread().getName());
		}
	
	}
}
