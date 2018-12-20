package com.revature.models;

public class CustomerAccount {

	int	accountNbr;
	String userName;
	String password;
	String acctType;
	double accountBal;
	
	public int getAccountNbr() {
		return accountNbr;
	}
	public void setAccountNbr(int accountNbr) {
		this.accountNbr = accountNbr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public double getAccountBal() {
		return accountBal;
	}
	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	
}
