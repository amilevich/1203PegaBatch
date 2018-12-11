package bank.main;

import java.util.ArrayList;

public class DataHub {

	public void initializeTempData() {
		/*
		 * Hashmap filled with random data for testing purposes
		 */
		Bank.data.put("cwest5960", new Registration()); // example
		Bank.data.put("cwest5961", new Employees("", "", "")); // example
		Bank.data.put("deast4530", new Customers("deast4530", "123", "CUSTOMER", "Daniel", "H", "Bilbo",
				"300 Tooth Fairy Way", "Mass", "Effect", 4033451928l,true, true, 3452, false, 100.0));
		Bank.data.put("beast4530", new Customers("beast4530", "123", "CUSTOMER", "Daniel", "W", "Bilbo",
				"300 Tooth Fairy Way", "Mass", "Effect", 4033451928l,true, true, 3452, false, 200.0));
		Bank.data.put("feast4530", new Customers("deast4530", "123", "CUSTOMER", "Daniel", "H", "Bilbo",
				"300 Tooth Fairy Way", "Mass", "Effect", 4033451928l,true, true, 3452, false, 50.0));
		Bank.login.put("cwest5496", new User("cwest5496", "dangIt", "EMPLOYEE"));
		Bank.login.put("deast4530", new User("deast4530", "123", "CUSTOMER"));
		Bank.login.put("beast4530", new User("beast4530", "123", "CUSTOMER"));
		Bank.login.put("feast4530", new User("feast4530", "123", "CUSTOMER"));
		Bank.login.put("admin", new User("admin", "123", "ADMIN"));
	}

	public void initialize() {
		ArrayList <String> jAcc = new ArrayList<String>();
		
		jAcc.add("Beast4530");
		jAcc.add("Feast4530");
		
		Bank.joint.put("cwest5496", jAcc);
	}
	public void addingJointAcc(String id) {
		ArrayList <String> jAcc = new ArrayList<String>();
		
		if(Bank.joint.containsKey(Bank.customer1.getId())) {
			jAcc = Bank.joint.get(Bank.customer1.getId());
			jAcc.add(id);
			Bank.joint.put(Bank.customer1.getId(), jAcc);
			
			if (Bank.joint.containsKey(id)) {
				jAcc = Bank.joint.get(id);
				jAcc.add(Bank.customer1.getId());
				Bank.joint.put(id, jAcc);
			}else {
				jAcc = new ArrayList<String>();
				jAcc.add(Bank.customer1.getId());
				Bank.joint.put(id, jAcc);
			}
		
		}
		
		
	}
	public void accountApproved(String id) {
		Bank.reg2 = (Registration) Bank.processing.get(id);
		Bank.data.put(Bank.reg2.getId(), new Customers(Bank.reg2.getId(), Bank.reg2.getPassWord(), "CUSTOMER", Bank.reg2.getFirstName(), Bank.reg2.getMiddleInitial(), Bank.reg2.getLastName(),
				Bank.reg2.getAddress(), Bank.reg2.getCity(), Bank.reg2.getState(), Bank.reg2.getPhoneNum(),Bank.reg2.getrAcc(), Bank.reg2.getjAcc(), Bank.reg2.getZip(), true, 0));
		Bank.login.put(Bank.reg2.getId(), new User(Bank.reg2.getId(), Bank.reg2.getPassWord(), "CUSTOMER"));
	}
	public void updateAccounts() {
		Bank.data.put(Bank.customer1.getId(), new Customers(Bank.customer1.getId(), Bank.customer1.getPassWord(), "CUSTOMER", Bank.customer1.getFirstName(), Bank.customer1.getMiddleInitial(), Bank.customer1.getLastName(),
				Bank.customer1.getAddress(), Bank.customer1.getCity(), Bank.customer1.getState(), Bank.customer1.getPhoneNum(),Bank.customer1.getrAcc(), Bank.customer1.getjAcc(), Bank.customer1.getZip(), Bank.customer1.getNewAccount(), Bank.customer1.getBalance()));
	}
	public void updateAccounts2() {
		Bank.data.put(Bank.customer2.getId(), new Customers(Bank.customer2.getId(), Bank.customer2.getPassWord(), "CUSTOMER", Bank.customer2.getFirstName(), Bank.customer2.getMiddleInitial(), Bank.customer2.getLastName(),
				Bank.customer2.getAddress(), Bank.customer2.getCity(), Bank.customer2.getState(), Bank.customer2.getPhoneNum(),Bank.customer2.getrAcc(), Bank.customer2.getjAcc(), Bank.customer2.getZip(), Bank.customer2.getNewAccount(), Bank.customer1.getBalance()));
	}
	public void addProcessing() {
		Bank.processing.put(Bank.reg.getId(),
				new Registration(Bank.reg.getFirstName(), Bank.reg.getMiddleInitial(), Bank.reg.getLastName(), Bank.reg.getAddress(),
						Bank.reg.getCity(), Bank.reg.getState(), Bank.reg.getZip(), Bank.reg.getPhoneNum(), Bank.reg.getrAcc(), Bank.reg.getjAcc(),
						Bank.reg.getNewAccount(), Bank.reg.getId(), Bank.reg.getPassWord(), Bank.reg.getUserType()));
	}

}
