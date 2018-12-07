
public class Two {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Fibonacci sequence
		int current = 0;
		int last = 0;
		for( int i = 1; i <= 25; i++) {
			
			int next = current + last;
			if (i > 0 && current == 0) {  //Doing this just to start it off right
				current++;
			}
			last = current;
			current = next;
			System.out.println(i + ": " + next);
		}
	}

}
