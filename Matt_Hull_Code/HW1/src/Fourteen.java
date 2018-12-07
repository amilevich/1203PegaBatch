import java.util.Date;

public class Fourteen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chars = { 'a', 'b', 'c' };
		for (char a : chars) {		//using a loop to do all cases
			switch (a) {
			case 'a':
				System.out.println(Math.sqrt(10));
				break;
			case 'b':
				Date date = new Date();
				System.out.println(date);
				break;
			default:
				String str = "I am learning core Java";
				String[] words = str.split(" ");
				for (String word : words) {
					System.out.println(word);
				}
			}
		}
	}

}
