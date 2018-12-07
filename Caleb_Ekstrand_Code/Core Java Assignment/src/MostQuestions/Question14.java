package MostQuestions;
import java.util.Calendar;

public class Question14 {

	public static void main(String[] args) {
		int myCase = 1; // the case you want (1-3)
		switch (myCase) { 
			case 1:
				System.out.println(Math.sqrt(9));
				break;
			case 2:
				Calendar today = Calendar.getInstance();
				System.out.println(today.getTime());
				break;
			case 3:
				String string = "I am learning Core Java";
				String array[] = string.split("");
				for (String i: array) {
					System.out.println(i);
				}
				break;
		}
	}

}
