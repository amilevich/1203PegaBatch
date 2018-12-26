package com.bank.dao;

import java.sql.SQLException;
import java.util.List;
import com.bank.beans.BankAdministrator;

public interface BankAdministratorDAO {
	// CRUD ops

	// create a super hero
	public abstract void createAccount(String accountid) throws SQLException;

	// get all of my accounts
	public abstract List<BankAdministrator> getBankAdministratorList() throws SQLException;

	// create classes, factory, modify pom.xml, create DAO, then make a dao for
	// powers class, now we deal with database.properties file/put it in project
	// structure so
	// we highlight name of project right clikc and new and
	// file...database.properties..a fully qualified class name
}
