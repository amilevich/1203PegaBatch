import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	private HashMap<String, ArrayList<Account>> accounts; // each person has a list of accounts
	private HashMap<String, WebAccount> webAccs;
	private ArrayList<String> potentialAccounts;
	private ArrayList<String> potentialJointAccounts;
	private HashMap<Account, String[]> jointAccountRecords; // this is used to keep a record of all users with a join
															// account
/*
 * the accounts variable is used to store all the accounts in the bank
 * each person can have more than one account, so each person has a dynamically sized list of accounts
 * 
 * the webAccs variable is used to store all the WebAccounts of the bank, these are the things created 
 * when a person registers with a username/password
 * 
 * potentialAccounts is simply a list of users that have applied to open an account
 * don't allow a user to have multiple open applications of the same type
 * 
 * potentialJointAccounts is same for joint accounts
 * user can have both open joinaccountapplication and openaccountapplication at same time
 * 
 * jointAccountRecords is for future functionality
 * 
 * About this class:
 * It is the backend, all inputs are taken from the Terminal class and sent here.
 * most methods are boolean so they return true of false depending on if they are able to execute successfully
 * ie, if you call the method to deposit money into a users account and the account is closed it will return false
 */
	public Server() {
		accounts = new HashMap<String, ArrayList<Account>>();
		webAccs = new HashMap<String, WebAccount>();
		WebAccount admin = new WebAccount("Admin", "Admin");  //THIS IS THE ONLY ADMIN ACCOUNT
		WebAccount employee = new WebAccount("Employee", "Employee");  //THIS IS THE ONLY EMPLOYEE ACCOUNT
		admin.setAccountType("Admin");
		employee.setAccountType("Employee");
		webAccs.put("Admin", admin);
		webAccs.put("Employee", employee);
		potentialAccounts = new ArrayList<String>();
		potentialJointAccounts = new ArrayList<String>();
	}
/*
 * this method takes in a username and password and creates a webaccount for a user if they do not put in first name and last name
 */
	public boolean addWebAccount(String username, String password) {
		if (webAccs.containsKey(username)) {
			return false;
		}
		webAccs.put(username, new WebAccount(username, password));
		return true;
	}
//creates web account for user with name
	public boolean addWebAccount(String username, String password, String fName, String lName) {
		if (webAccs.containsKey(username)) {
			return false;
		}
		webAccs.put(username, new WebAccount(username, password, fName, lName));
		return true;
	}
/*
 * validates sent username and password against WebAccounts
 */
	public boolean logIn(String username, String password) {
		if (webAccs.containsKey(username)) {
			WebAccount acc = webAccs.get(username);
			if (acc.checkPassword(password)) {
				return true;
			}

		}
		return false;
	}
	/*
	 * allows person to change their first name and last name
	 * this method is never called
	 */
	public boolean setWebAccName(String username, String fName, String lName) {
		if (!webAccs.containsKey(username))
			return false;
		WebAccount acc = webAccs.get(username);
		acc.setName(fName, lName);
		return true;

	}
//checks if this username exists in the bank
	public boolean hasWebAccount(String username) {
		return webAccs.containsKey(username);
	}
//submits an application for an account
	public boolean enterApplication(String username) {
		if (potentialAccounts.contains(username)) {
			return false;
		}
		potentialAccounts.add(username);
		return true;
	}
//get all the current applicants 
	public ArrayList<String> getApplicants() {
		return (ArrayList<String>) potentialAccounts.clone();
	}
	//get all the current joint applicants
	public ArrayList<String> getJointApplicants(){
		return (ArrayList<String>) this.potentialJointAccounts.clone();
	}
//gets the data pertaining to a certain customer, then puts it all into a CustomerInfo object then sends it
	//to the front end to display
	public CustomerInfo getCustomerInfo(String username) {
		WebAccount customer = webAccs.get(username);
		if (accounts.containsKey(username)) {
			System.out.println("1");
			return new CustomerInfo(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
					accounts.get(username), webAccs.containsKey(username));
		}
		return new CustomerInfo(customer.getFirstName(), customer.getLastName(), customer.getUsername(),
				webAccs.containsKey(username));
	}
//allows for an account application to be approved and an account to be created
	public boolean approveAccount(String username) {
		if (!potentialAccounts.contains(username))
			return false;
		int id = this.getMaxAccId(username) + 1;
		Account acc = new Account(id);
		this.finishAccountAddition(username, acc);
		potentialAccounts.remove(username);
		return true;
	}
//checks if a user already has an open account application
	public boolean hasAccountPending(String username) {
		return potentialAccounts.contains(username);
	}
//denies an account application
	public boolean declineAccount(String username) {
		if (potentialAccounts.contains(username)) {
			potentialAccounts.remove(username);
			return true;
		}
		return false;
	}

//gets whether web account is customer, employee, or admin
	public String getAccountType(String username) {
		return webAccs.get(username).getAccountType();
	}
//this just gets the accounts belonging to a provided user and converts that to an ArrayList<String> to send back
	public ArrayList<String> getAccountInfo(String username) {
		if (this.accounts.containsKey(username)) {
			ArrayList<Account> accs = accounts.get(username);
			ArrayList<String> accInfos = new ArrayList<String>();
			for (Account a : accs) {
				accInfos.add(a.toString());
			}
			return accInfos;
		}
		return new ArrayList<String>();
	}
//check if this account exists  (any account can be identified by combo of username and id)
	public boolean validAccountID(String username, int id) {
		if (this.accounts.containsKey(username)) {
			ArrayList<Account> accs = accounts.get(username);
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for (Account acc : accs) {
				ids.add(acc.getId());
			}
			return ids.contains(id);
		}
		return false;
	}
//deposits money into specified account
	public boolean deposit(String username, int id, double amount) {
		if (this.accounts.containsKey(username)) {
			ArrayList<Account> accs = accounts.get(username);
			for (Account acc : accs) {
				if (acc.getId() == id && acc.canDeposit()) {
					acc.deposit(amount);
					return true;
				}
			}
		}
		return false;
	}
//withdraws money from specified account
	public boolean withdraw(String username, int id, double amount) {
		if (this.accounts.containsKey(username)) {
			ArrayList<Account> accs = accounts.get(username);
			for (Account acc : accs) {
				if (acc.getId() == id) {
					if (acc.canWithdraw(amount)) {
						acc.withdraw(amount);
						return true;
					}
				}
			}
		}
		return false;
	}
//initiates transfer from one account to another
	public boolean transferMoney(String initatior, int initId, String recepient, int recipId, double amount) {
		boolean possible = accounts.containsKey(initatior);
		possible = possible && accounts.containsKey(recepient);
		if (!possible) {
			return false;
		}
		ArrayList<Account> initAccs = accounts.get(initatior);
		ArrayList<Account> recipAccs = accounts.get(recepient);
		Account initAcc = null;
		Account recipAcc = null;
		for (Account acc : initAccs) {
			if (acc.getId() == initId) {
				initAcc = acc;
			}
		}
		for (Account acc : recipAccs) {
			if (acc.getId() == recipId) {
				recipAcc = acc;
			}
		}
		if (initAcc == null || recipAcc == null) {
			return false;
		}
		possible = initAcc.canWithdraw(amount) && recipAcc.canDeposit();
		if (possible) {
			initAcc.withdraw(amount);
			recipAcc.deposit(amount);
		}

		return true;
	}
	//checks if user has bank account
	public boolean hasAccount(String username) {
		return accounts.containsKey(username);
	}
//closes an open bank account (NOT APPLICATION, FULL ACCOUNT, ACCOUNT CAN HAVE MONEY IN IT)
	public boolean closeAccount(String username, int id) {
		if (!accounts.containsKey(username)) {
			return false;
		}
		ArrayList<Account> accList = accounts.get(username);
		for (Account acc : accList) {
			if (acc.getId() == id) {
				acc.closeAccount();
				return true;
			}
		}
		return false;
	}
//opens joint account application
	public boolean applyForJointAccount(String username, String partnerName) {
		if (!webAccs.containsKey(username) || !webAccs.containsKey(partnerName)) {
			return false;
		}
		if (!webAccs.get(partnerName).getAccountType().equals("Customer")) {
			return false;
		}
		if (username.equals(partnerName)) {
			return false;
		}
		String partnership =  username + " " + partnerName; //new String[] { username, partnerName };
		String shipPartner =  partnerName + " " + username;// new String[] { partnerName, username };
		if (potentialJointAccounts.contains(partnership) || potentialJointAccounts.contains(shipPartner)) {
			return false;
		}
		potentialJointAccounts.add(partnership);
		return true;
	}
//used when making joint accounts, to make sure that the one account that is shared between both users has one id that
	//will be unique in lists of both users accounts
	private int getMaxAccId(String username) {
		if (!accounts.containsKey(username)) {
			return -1;
		}
		ArrayList<Account> accs = accounts.get(username);
		int maxId = 0;
		for (Account acc : accs) {
			maxId = Math.max(maxId, acc.getId());
		}
		return maxId;
	}
//approves joint account application
	public boolean approveJointAccountApplication(String username, String partnerName) {
		String partnership = username + " " + partnerName;
		String shipPartner = partnerName + " " + username;
		if (potentialJointAccounts.contains(partnership)) {
			int maxId = Math.max(getMaxAccId(username), getMaxAccId(partnerName)) + 1;
			Account newAcc = new Account(maxId);
			if (finishAccountAddition(username, newAcc) && finishAccountAddition(partnerName, newAcc)) {

			}
		} else if (potentialJointAccounts.contains(shipPartner)) {
			int maxId = Math.max(getMaxAccId(username), getMaxAccId(partnerName)) + 1;
			Account newAcc = new Account(maxId);
			if (finishAccountAddition(username, newAcc) && finishAccountAddition(partnerName, newAcc)) {
				potentialJointAccounts.remove(shipPartner);
			}
		}
		return false;
	}
	/*
	 * abstracted this code because I was using it too much
	 */
	private boolean finishAccountAddition(String username, Account acc) {
		if (accounts.containsKey(username)) {
			ArrayList<Account> acs = accounts.get(username);
			int id = acs.size();
			acs.add(acc);
			return true;
		}
		ArrayList<Account> custaccs = new ArrayList<Account>();
		custaccs.add(acc);
		accounts.put(username, custaccs);
		return true;
	}
	//declines a join account
	public boolean declineJointAccount(String username, String partnerName) {
		String partnership =  username + " " + partnerName;//new String[] { username, partnerName };
		String shipPartner =  partnerName + " " + username;//new String[] { partnerName, username };
		if(this.potentialJointAccounts.contains(partnership)){
			potentialJointAccounts.remove(partnership);
			return true;
		}
		else if(this.potentialJointAccounts.contains(shipPartner)) {
			potentialJointAccounts.remove(shipPartner);
			return true;
		}
		return false;
	}
}
