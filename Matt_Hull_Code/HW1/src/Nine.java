import java.util.ArrayList;

public class Nine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i <= 100; i++) {  //  by definition prime is greater than 1
			boolean isPrime = true;
			for(int prime : primes) {
				isPrime = isPrime && (i % prime != 0); //if number isn't prime, it's divisible by a prime, so only checking
				if (!isPrime)							//against primes
					break;								
			}				
			if (isPrime)
				primes.add(i);
		}
		System.out.println(primes);
	}

}
