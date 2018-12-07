package hw.weekone;

import java.util.ArrayList;

public class Q12 {
	public static void evens() {
		int arrayL[] = new int[100];
		for (int i = 1; i <= 100; i++) {
			arrayL[i - 1] = i;
		}
		for (int i = 0; i < 100; i++) {
			if (arrayL[i] % 2 == 0) {
				System.out.println(arrayL[i]);
			}
		}

	}

}
