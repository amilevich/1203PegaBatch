//Author: Steven Jean-Paul
//Q12 - Enhanced For Loop
public class EvenEnhancedFor {

		public static void main(String[] args) {
			int numStore[] = new int[100]; //fill with string objects
			

			for (int i = 1; i < 101; i++) { //store numbers 1 to 100.
				numStore[i-1] = i; //filled with string numbers objects
			}	
			
			for (int n = 0; n < (numStore.length); n++) {
					if ((numStore[n] % 2) == 0) {
						System.out.println(numStore[n]);
					}
			}
		}
}
