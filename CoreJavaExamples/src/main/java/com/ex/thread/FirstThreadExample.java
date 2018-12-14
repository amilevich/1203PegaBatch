package com.ex.thread;

public class FirstThreadExample {
	public static void main(String[] args) throws InterruptedException {
		
		
		Thread mainThread = Thread.currentThread();
		
		boolean isDaemon = mainThread.isDaemon();
		System.out.println(isDaemon);
		
		Thread.sleep(2000);
		
		
		String name = mainThread.getName();
		
		
		System.out.println("name: " + name);
		
		
		
		
		
	}
}
