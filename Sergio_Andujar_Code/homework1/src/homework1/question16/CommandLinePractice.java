package homework1.question16;

/**
 * command line input 
 * @author Sergio Andujar
 * @version 1.8
 */

public class CommandLinePractice {
	
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			//display the length of each value in the arg
			System.out.println(args[0].length());
		}
	}

}
