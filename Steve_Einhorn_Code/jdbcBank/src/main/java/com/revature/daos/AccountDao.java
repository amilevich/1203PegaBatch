package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	
	public boolean getAccount(int accountNbr);
	public boolean getCustomerAccount(int accountNbr, String userName);
	public List getCustomerAccounts();
	public void saveAccount(Account account);
	public void updateAccountBalance(Account account);

}
