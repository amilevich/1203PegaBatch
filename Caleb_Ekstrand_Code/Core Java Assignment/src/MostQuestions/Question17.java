package MostQuestions;
import java.util.Scanner;

public class Question17 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //makes a new scanner that has standard input as its input
		// get each input
		System.out.println("Principal: "); 
		int principal = sc.nextInt();
		System.out.println("Rate(for 5% put .05): ");
		double rate = sc.nextDouble();
		System.out.println("Time: ");
		int time = sc.nextInt();
		sc.close();
		System.out.println("Interest: " + (principal * rate * time)); // calculates interest with inputs given
	}

}
