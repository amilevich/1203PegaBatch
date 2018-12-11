package projectPartZero;

import java.util.ArrayList;

public class Customer implements User{
	
	private String name;
	private String userName;
	private String ss;
	private String password;
	private String accountNumber;
	private ArrayList<Account> ownedAccounts = new ArrayList<Account>();
	
	Customer(String aName, String userName, String ss, String password){
		this.name = aName;
		this.userName = userName;
		this.ss = ss;
		this.password = password;
		
	}
	
	Customer(String aName, String userName, String ss, String password, String id){
		this.name = aName;
		this.userName = userName;
		this.ss = ss;
		this.password = password;
		this.accountNumber = id;
	}
	
	@Override
	public String getName() {
		return this.name;
		
	}
	
	@Override
	public String getUserName() {
		return this.userName;
		
	}
	
	@Override
	public String toString() {
		return "Customer name:" + name + ", userName:" + userName + ", ss:" + ss + ", password:" + password
				+ ", accountNumber:" + accountNumber;
	}
	
	public int getNumberOfAccounts() {
		return this.ownedAccounts.size();
	}
	
	public void displayAccounts() {
		for(int i = 0; i < this.getAccounts().size(); i++) {
			System.out.println("Account Number: " + this.getAccountNumber() + " | " + "Balance: " + this.getAccounts().get(i).getBalance());
		}
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public ArrayList<Account> getAccounts() {
		return this.ownedAccounts;
	}
	
	public String getSocialSecurity() {
		return this.ss;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void storeAccount(Account aAccount) {
		this.ownedAccounts.add(aAccount);
	}

	public Account getAccount(int index) {
		return this.ownedAccounts.get(index);
	}
	
}
