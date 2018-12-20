package com.revature.dao;

import java.io.IOError;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.classes.Account;
import com.revature.classes.BankAdmin;
import com.revature.classes.Customer;
import com.revature.classes.Employee;

public interface RetrievalDao {
	
	public void removeLogin(int id)throws SQLException, IOError;
	public void dropTables() throws SQLException, IOError;
	public void removeDeclined(Customer rejected) throws SQLException, IOError; 
	public void removeFromUserAccount(int cusid, int accid)throws SQLException, IOError;
	public void removeFromUserAccount(int id)throws SQLException, IOError;
	public ArrayList<Account> getAllAccounts()throws SQLException, IOError;
	//public Account getAccount(int id) throws SQLException, IOError;
	//public void getUserAndAccount(int id) throws SQLException, IOError;
	public void insertIds(int cus, int acc) throws SQLException, IOError;
	public void createAccount(int id) throws SQLException, IOError;
	public void updateCustomerToRegistered(Customer customer) throws SQLException, IOError;
	public ArrayList<Customer> getUnregisteredUsers() throws SQLException, IOError;
	public void updateAccount(Account account)throws SQLException, IOError;
	public ArrayList<Account> getUserAccounts(int id)throws SQLException, IOError;
	public Customer getACustomer(String givenUserName, String givenPassword)throws SQLException, IOError;
	public Employee getAEmployee(String givenUserName, String givenPassword)throws SQLException, IOError;
	public BankAdmin getABankAdmin(String givenUserName, String givenPassword)throws SQLException, IOError;
	public ArrayList<Customer> getAllCustomer()throws SQLException, IOError;
	public void removeCustomer(Customer customer)throws SQLException, IOError;
	public void removeAccount(int id)throws SQLException, IOError;
//	public void createJointApplication(String firstName, String lastName, String userName, 
//			String passWord, String anfirstName, 
//			String anLastName, String userName2, String passWord2)throws SQLException, IOError;
	void createApplication(String firstName, String lastName, String alias, String passWord) throws SQLException, IOError;

}
