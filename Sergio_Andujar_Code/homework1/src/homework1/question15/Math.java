package homework1.question15;

/**
 * doing mathOperations with interface
 * @author Sergio Andujar
 * @version 1.8
 */

public interface Math {
	
	public void addition(int num1, int num2);
	public void substraction(int num1, int num2);
	public void multiplication(int num1, int num2);
	public void division(int num1, int num2);

}

class MathOperations implements Math {
	
	@Override
	public void addition(int num1, int num2) {
		System.out.println(num1 + num2);
	};
	
	@Override
	public void substraction(int num1, int num2) {
		System.out.println(num1 - num2);
	};
	
	@Override
	public void multiplication(int num1, int num2) {
		System.out.println(num1 * num2);
	};
	
	@Override
	public void division(int num1, int num2) {
		System.out.println(num1 / num2);
	}
	
}

class main{
	
	public static void main(String[] arg) {
		MathOperations calc = new MathOperations();
		calc.addition(2, 2);
		calc.substraction(2, 2);
		calc.multiplication(2, 4);
		calc.division(1000, 10);
	}
	
	
}

