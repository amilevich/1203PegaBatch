package com.ex.thread.producerconsumer;

public class ProducerConsumerExample {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		CubbyHole c = new CubbyHole();
		Runnable producerJob = new Producer(c);
		Runnable consumerJob = new Consumer(c);
		
		Thread producerWorker = new Thread(producerJob, "PRODUCER");
		Thread consumerWorker = new Thread(consumerJob, "CONSUMER");
		
		producerWorker.start();
		consumerWorker.start();
	}
}

class CubbyHole {
	private int contents;
	private boolean available = false;

	public synchronized int get() {
		
		while (available == false) {
			try {
				System.out.println("\t\t\t" + Thread.currentThread().getName() + " called waiting");
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		notifyAll();
		return contents;
	}

	public synchronized void put(int value) {
		while (available == true) {
			try {
				System.out.println("\t\t\t" + Thread.currentThread().getName() + " called waiting");
				wait();
			} catch (InterruptedException e) {
			}
		}
		contents = value;
		available = true;
		notifyAll();
	}
}

class Consumer implements Runnable {
	private CubbyHole cubbyhole;

	public Consumer(CubbyHole c) {
		cubbyhole = c;
	}

	public void run() {
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = cubbyhole.get();
			System.out.println(Thread.currentThread().getName() + " got: " + value);
		}
	}
}

class Producer implements Runnable {
	private CubbyHole cubbyhole;

	public Producer(CubbyHole c) {
		this.cubbyhole = c;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			cubbyhole.put(i);
			System.out.println(Thread.currentThread().getName() + " put: " + i);
			try {
				Thread.sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
		}
	}
}
