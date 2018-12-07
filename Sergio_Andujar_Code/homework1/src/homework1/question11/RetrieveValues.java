package homework1.question11;

import homework1.FloatValues.Cat;

/**
 * Importing your own packages
 * @author Sergio Andujar
 * @version 1.8
 */

public class RetrieveValues {
	
	public static void main(String[] arg) {
		
		// importing cat class and instantiating a cat object
		Cat tom = new Cat();
		System.out.println(tom.getAge());
		System.out.println(tom.getLives());
		
	}

}
