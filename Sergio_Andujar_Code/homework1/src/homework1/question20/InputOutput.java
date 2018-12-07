package homework1.question20;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 

/**
 * file manipulation 
 * @author Sergio Andujar
 * @version 1.8
 */

public class InputOutput {
	public static void main(String[] arg) {
		File file = new File("Data.txt");
		// try with resources
		// try to instantiate scanner object to read in a file
		try(Scanner scan = new Scanner(file)){
			while(scan.hasNext()){
				String line = new String(" ");
				line += scan.nextLine();
				
				// split the data on : and put them into the array
				String[] data = line.split(":");
				
				System.out.println("Name: " + data[0]+ " " + data[1]);
				System.out.println("Age: " + data[2]);
				System.out.println("State: " + data[3]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
