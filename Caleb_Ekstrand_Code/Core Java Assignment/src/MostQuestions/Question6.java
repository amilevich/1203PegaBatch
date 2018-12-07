package MostQuestions;

public class Question6 {
	public static void main(String[] args) {
		int num = 86745;
		int testNum = num; // what we will work on as to not lose the initial value of num
		while (testNum > 2) {
			testNum -= 2; // all even nums will -= 2 down to zero while all odds will -= 2 down to 1
			// System.out.println(testNum);
		}
		if (testNum == 1) {
			System.out.println(num + " is Odd");
		} else if (testNum == 0) {
			System.out.println(num + " is Even");
		}
	}
}
