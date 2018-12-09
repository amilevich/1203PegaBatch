package bank.main;

public class DataHub {
	
	public void initializeTempData() {
		/*
		 * Hashmap filled with random data for testing purposes
		 */
		Bank.data.put("cwest5960", new Registration()); // example
		Bank.data.put("cwest5961", new Employees("", "", "")); // example
		Bank.login.put("cwest5496", new User("cwest5496", "dangIt", "EMPLOYEE"));
	}

}
