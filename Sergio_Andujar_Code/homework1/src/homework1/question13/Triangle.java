package homework1.question13;

/**
 * Altered Binary Triangle
 * @author Sergio Andujar
 * @version 1.8
 */

public class Triangle {
	public static void main(String[] arg) {
		for(int row = 1; row <= 4; row++) {
			for(int col = 1; col <= row; col++) {
				//
				if(row <= 2) {
					// by adding the row and col then getting it's remainder
					// we get the ones and zeros
					System.out.print(((row+col)%2) + " ");
				}
				else{
					// this is for the second half of the triangle
					// if you use the formula above, you get 010 and 1010
					// by adding one, it changes to 101 and 0101
					System.out.print(((row + 1 + col)%2) + " ");
				}
			}
		
			System.out.println();
			
		}
	}
}


				
	

