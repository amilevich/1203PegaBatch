
public class Six {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 345;
		int x = num / 2;
		//seeing if casting it to double then dividing it makes it different
		double y = (double)num / 2;
		boolean ans = Double.compare(x,y)==0;
		System.out.println(ans);

	}

}
