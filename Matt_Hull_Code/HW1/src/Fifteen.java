
public class Fifteen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Making a calculator, then calculating
		//Thought about making the class a singleton after I had done this
		//Didn't do that.
		//Though it seems static methods would be more appropriate but that answer doesn't meet requirements
		SimpleCalculator calc = new SimpleCalculator();
		System.out.println("5 + 6 = " + calc.addition(5, 6));
		System.out.println("5 - 6 = " + calc.subtraction(5, 6));
		System.out.println("5 * 6 = " + calc.multiplication(5, 6));
		System.out.println("5 / 6 = " + calc.division(5, 6));
	}

}
