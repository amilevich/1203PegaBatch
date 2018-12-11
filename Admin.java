package project0.bank;

public class Admin extends Users{
	Admin(String firstName,String lastName, String username, String password){
		//Bank bank= Bank.createBank();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setType(UserType.Admin);
		//bank.addToAdmins(this);
		//bank.addToUsers(this);
	}

	public void approveAplication(Applications a) {
		a.approveAccount();

	}

	public void deleteAccount(Accounts a) {
		

	}

	public void withdrawFromAccount(Accounts a, Double amount) {
		a.withdraw(amount);
	}

	public void depositInAccount(Accounts a, Double amount) {
		a.deposit(amount);
	}

	public void transferBetweennAccount(Accounts a, Accounts b, int amount) {
		a.transferFunds(amount, b);
	}
	public void getAllAccounts() {
		
	}



}
