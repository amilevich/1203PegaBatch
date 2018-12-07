package hw.weekone;

public class Q15 implements Q15Interface{

	@Override
	public void addition(double a, double b) {
		System.out.println(a+b);
		
	}

	@Override
	public void subtraction(double a, double b) {
		System.out.println(a-b);
		
	}

	@Override
	public void multiplication(double a, double b) {
		System.out.println(a*b);
		
	}

	@Override
	public void division(double a, double b) {
		System.out.println(a/b);
		
	}
	

}
