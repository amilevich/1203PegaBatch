import java.util.ArrayList;

public class Nineteen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> listy = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) {
			listy.add(i+1);  //populating that arraylist
		}
		int total = 0;
		for(int num : listy) {
			total += num;	//getting that total
		}
		System.out.println(total); //printing out that total
		total = 0;
		for(int num : listy) {
			if(num % 2 == 0) // checking them numbers for them evenness
				total += num;  //summin up them evens
		}
		System.out.println(total);
		total = 0;
		for(int num : listy) {
			if(num % 2 != 0) //Same stuff, but odds
				total += num;
		}
		System.out.println(total);
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i <= 10; i++) {  //  This bit is just getting a list of prime numbers below 10
			boolean isPrime = true;		//stole this from the code for the other question regarding primes
			for(int prime : primes) {	//figure you might not like just hard-coding in the primes
				isPrime = isPrime && (i % prime != 0); 
				if (!isPrime)
					break;
			}				
			if (isPrime)
				primes.add(i);
		}
		
		
//		for(int i = 1; i <= 10; i++) {
//
//				
//			if (primes.contains(i)) {
//				listy.remove((Integer)i);
//			}
//		}
		
		//Figured out cooler way 

		
		listy.removeAll(primes);  //send it all the crap I want gone and it's gone; awesome
		
		
		System.out.println(listy);
		
	}

}
