package hw.weekone;

public class Q13 {
	public static void writeTriangle() {
		int printingNumber=0;
		for(int i=0; i<4;i++) {
			for (int j=0; j<=i;j++) {
				if (j==i) {
					System.out.print(printingNumber+" ");
					System.out.println();
				}
				else {
					System.out.print(printingNumber+" ");
				}
				printingNumber=(printingNumber+1)%2;
			}
		}
		
	}

}
