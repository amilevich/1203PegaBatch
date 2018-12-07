import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Twenty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("Data.txt");	//using the fancy try catch 
		try(Scanner scan = new Scanner(file)){ //Using Scanner to read in file
			while(scan.hasNextLine()) {		// need to compare to other methods of reading files
				String line = scan.nextLine();
				String[] info = line.split(":");  //split string on colons, as expected
				System.out.println("Name: " + info[0] + " " + info[1]);
				System.out.println("Age: " + info[2] + " years");
				System.out.println("State: " + info[3] + " state\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
