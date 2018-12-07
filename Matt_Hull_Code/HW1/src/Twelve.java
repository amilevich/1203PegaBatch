
public class Twelve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = new int[100];  //question seems too simple, missing something
		for(int i = 0; i < 100; i++) {
			numbers[i] = i+1;
		}
		for(int num : numbers) {  //Inscrutably Complex for loop that somehow outputs only even numbers
			if(num % 2 == 0)
				System.out.println(num);
		}
	}

}
