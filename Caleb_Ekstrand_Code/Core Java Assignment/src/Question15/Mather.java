package Question15;

public class Mather implements Mathy{

	@Override
	public int addition(int a, int b) {
		return a+b;
	}

	@Override
	public int subtraction(int a, int b) {
		return a-b;
	}

	@Override
	public int division(int a, int b) {
		return a/b;
	}

	@Override
	public int multiplication(int a, int b) {
		return a*b;
	}

}
