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
		Bank.reg = (Registration) Bank.processing.get(id);
		Bank.data.put(Bank.reg.getId(), new Customers(Bank.reg.getId(), Bank.reg.getPassWord(), "CUSTOMER", Bank.reg.getFirstName(), Bank.reg.getMiddleInitial(), Bank.reg.getLastName(),
				Bank.reg.getAddress(), Bank.reg.getCity(), Bank.reg.getState(), Bank.reg.getPhoneNum(),Bank.reg.getrAcc(), Bank.reg.getjAcc(), Bank.reg.getZip(), true, 0));
		Bank.login.put(Bank.reg.getId(), new User(Bank.reg.getId(), Bank.reg.getPassWord(), "CUSTOMER"));
	}

}
