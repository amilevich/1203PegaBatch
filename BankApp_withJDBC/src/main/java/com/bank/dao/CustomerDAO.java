package com.bank.dao;

import java.sql.SQLException;

public interface CustomerDAO {

	// create a super hero
	abstract void createCustomer(String fn, String ln, int streetNum, String streetName, String city, String cstate,
			int zipcode, long phone, String email, int accid, int pin, String un, String pw) throws SQLException;

}
