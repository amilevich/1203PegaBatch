package MostQuestions;

public class Question13 {

	public static void main(String[] args) {
		printPyramid(4); // choose how deep you want your pyramid 
	}

	public static void printPyramid(int depth) {
		String tracker1 = ""; //holds the line build up
		int side = 0; // side = 0 places on the left and side = 1 places on the right
		int num = 0; // the number to be placed
		int time = 1; // must place 2 zeros or 2 ones before switching, time keeps track of how many of a number were placed
		for (int i = 0; i < depth; i++) {
			//if i is odd place on left
			if (i % 2 != 0) {
				side = 0;
			} else {
				side = 1;
			}
			//if current num has been placed twice, switch to other num
			if (time == 2) {
				time = 0;
				if (num == 0) {
					num = 1;
				} else {
					num = 0;
				}
				
			}
			//placing on left
			if (side == 0) {
				tracker1 = num + " " +  tracker1;
			} else {
			//placing on right
				tracker1 = tracker1 + num + " ";
			}
			time++; //an instance of num was placed, uptick time!
			//print that line
			System.out.println(tracker1);
		}
	}
}
