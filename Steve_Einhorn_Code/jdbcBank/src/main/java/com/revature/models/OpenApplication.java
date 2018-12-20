package com.revature.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OpenApplication {
	
	public OpenApplication() {}
	
	public OpenApplication(String username, String password, String acctType, double iDepo) {
		this.username = username;
		this.password = password;
		this.acctType = acctType;
		this.initDeposit = iDepo;
	}

	int	id;
	String username;
	String password;
	String acctType;
	double initDeposit;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Double getInitDeposit() {
		return initDeposit;
	}
	public void setInitDeposit(Double initDeposit) {
		this.initDeposit = initDeposit;
	}
	
	public void makeOpenApplication() throws IOException {
		
		FileWriter fw = new FileWriter("OpenApplications.txt", true); 
		BufferedWriter bwr = new BufferedWriter(fw);
		
		bwr.write(getUsername() + ":" + 
				  getPassword() + ":" + 
				  getAcctType() + ":" + 
				  getInitDeposit() );
		bwr.newLine();
		bwr.close();
		
		System.out.println("Open Application created !");
		
	}

}
