package project0.bank;

public class Employee extends Users{
	
	Employee(String firstName,String lastName, String username, String password){
		//Bank bank= Bank.createBank();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setType(UserType.Employee);
		//bank.addToEmployees(this);;
		//bank.addToUsers(this);
	}

}
