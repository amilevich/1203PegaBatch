package project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import project.account.Account;

public interface CustomerAccDAO {
	public abstract void createPair(String username, int acc_num) throws SQLException;
	public abstract ArrayList<Account> getAccounts(String username) throws SQLException;
}
