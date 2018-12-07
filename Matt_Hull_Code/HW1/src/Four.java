
public class Four {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int total = 1;
		//Does the factorial thing
		for(int i = 2; i <= n; i++) {  //Start at 2, no waste time, hurry hurry
			total *= i;
		}
		System.out.println(total);
	}

}
