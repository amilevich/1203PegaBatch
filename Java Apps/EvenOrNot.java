import java.util.Scanner;
//Author: Steven Jean-Paul
//Q6 - Even or not
public class EvenOrNot {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a number to determine if it is even: ");
		
		String x = in.nextLine();
		
		//total size = x.length(). -1 = last value. find the ones place value position
		
		String zero = "0";
		String two = "2";
		String four = "4";
		String six = "6";
		String eight = "8";
		//now compare that last value position to my set of stringed numbers to see if it's even
		//finalOnesPlace.charAt(index)
		if (x.substring(x.length()-1).equals(zero)){
			System.out.println("Even");
		}
		if (x.substring(x.length()-1).equals(two)){//finalOnesPlace.equals(two)
			System.out.println("Even");
		}
		if (x.substring(x.length()-1).equals(four)){//finalOnesPlace.equals(four)
			System.out.println("Even");
		}
		if (x.substring(x.length()-1).equals(six)){//finalOnesPlace.equals(six)
			System.out.println("Even");
		}
		if (x.substring(x.length()-1).equals(eight)){//finalOnesPlace.equals(eight)
			System.out.println("Even");
		}
		if( !((x.substring(x.length()-1).equals(zero)) || (x.substring(x.length()-1).equals(two)) || (x.substring(x.length()-1).equals(four)) || (x.substring(x.length()-1).equals(six)) || (x.substring(x.length()-1).equals(eight))) ) {
			System.out.println("Not an even number.");
		}
		 
		
		in.close();

	}

}
