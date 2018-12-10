package bank.main;

public class DataHub {

	public void initializeTempData() {
		/*
		 * Hashmap filled with random data for testing purposes
		 */
		Bank.data.put("cwest5960", new Registration()); // example
		Bank.data.put("cwest5961", new Employees("", "", "")); // example
		Bank.data.put("deast4530", new Customers("deast4530", "123", "CUSTOMER", "Daniel", "H", "Bilbo",
				"300 Tooth Fairy Way", "Mass", "Effect", 4033451928l, 3452, "Saving", false, 100.0));
		Bank.login.put("cwest5496", new User("cwest5496", "dangIt", "EMPLOYEE"));
	}

}
