import java.text.DecimalFormat;
import java.util.Scanner;

public class Seventeen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Using Scanner to get the input
		Scanner scan = new Scanner(System.in);
		System.out.println("WHAT IS THE PRINCIPAL!?");
		double principal = scan.nextDouble();	//Figure principal could be a decimal
		System.out.println("OK NOW! WHAT IS THE INTEREST RATE PER YEAR?!?!");
		double interestrate = scan.nextDouble(); //This is decimal
		System.out.println("ALRIGHTY THEN, HOW MANY OF THEM YEARS IT BEEN?!");
		int time = scan.nextInt();  //Figure int's better here
		double interest = principal * interestrate * time;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("INTEREST, IT BE $" + df.format(interest) + "!!!");
		scan.close();
	}

}
