package com.example.exception;

public class ExampleDriver {

	public static void main(String[] args) throws Exception {

		bankTransaction("Withdraw", 101);

	}

	static void printPositiveNumber(int x) {
		if (x < 0)
			throw new LessThanZeroException();
		else
			System.out.println("x: " + x);
	}

	static void bankTransaction(String action, double amount) throws BankTransactionException {
		if (action.equals("Withdraw")) {
			try {
				withdraw(amount);

			} catch (Error e) {
				System.out.println("catching error");// bad idea, errors are fatal
			} catch (WithdrawTooBigException e) {
				throw new BankTransactionException("There is something wrong with your bank transaction.", e);
			} finally {
				// finally executes after a try/catch
				// except hardware destroyed, System.exit(), fatal error
				System.out.println("in finally block");
			}
			System.out.println("outside of finally");
			// this line could be unreachable if all exceptions above are ducked
		}

	}

	public static void withdraw(double withdrawamount) throws WithdrawTooBigException { // ducking

		double maxwithdrawamount = 100;

		if (withdrawamount > maxwithdrawamount) {
			throw new WithdrawTooBigException(
					withdrawamount + " exceeds the max withdraw limit of:  " + maxwithdrawamount);
		}
	}

}
