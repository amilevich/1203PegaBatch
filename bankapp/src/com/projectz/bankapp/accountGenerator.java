<<<<<<< HEAD
package com.projectz.bankapp;

public class accountGenerator {
	//private double ranAcc = 0;
	
	public static int accGen() {
		double ran = Math.random();
		double rann = ran * 100000;
		Double rannn = (Double)Math.ceil(rann);
		int ranAcc = rannn.intValue();
		return ranAcc;
	}
}
=======
package com.projectz.bankapp;

public class accountGenerator {
	//private double ranAcc = 0;
	
	public static int accGen() {
		double ran = Math.random();
		double rann = ran * 100000;
		Double rannn = (Double)Math.ceil(rann);
		int ranAcc = rannn.intValue();
		return ranAcc;
	}
}
>>>>>>> a8da14f2dc04a2a9e857f8f24d3c9c9e58e318da
