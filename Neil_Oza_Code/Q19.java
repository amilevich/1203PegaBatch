package hw.weekone;

import java.util.ArrayList;

public class Q19 {
	public static void numberFun() {
		ArrayList<Integer> arrayL = new ArrayList<Integer>();
		for (int i = 1; i < 11; i++) {
			arrayL.add(i);

		}
		for (int x : arrayL) {
			System.out.println(x);
		}
		int evenSum = 0;
		for (int x : arrayL) {
			if (x % 2 == 0) {
				evenSum = evenSum + x;
			}
		}
		System.out.println(evenSum);
		int oddSum = 0;
		for (int x : arrayL) {
			if (x % 2 == 1) {
				oddSum = oddSum + x;
			}
		}
		System.out.println(oddSum);
		ArrayList<Integer> composites= new ArrayList<Integer>();
		composites.add(1);
		for (int x : arrayL) {
			boolean composite = false;
			for (int i = 2; i < x; i++) {
				if (x % i == 0) {
					composite = true;
				}
			}
			if(composite) {
				composites.add(x);
			}
		}

		for (int x : composites) {
			System.out.println(x);
		}
		

	}

}
