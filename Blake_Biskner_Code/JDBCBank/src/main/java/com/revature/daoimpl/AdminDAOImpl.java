package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.bean.Customer;
import com.revature.dao.AdminDAO;
import com.revature.util.ConnFactory;

public class AdminDAOImpl implements AdminDAO{
	public static ConnFactory cf=ConnFactory.getInstance();
	
	@Override
	public void deleteAccount(Customer customer) throws SQLException{
		Connection conn=cf.getConnection();
		String sql="{ call DELETE_ACCOUNT(?)";
		CallableStatement call=conn.prepareCall(sql);
		
		call.setInt(1, customer.getAcctNum());
		
		call.execute();
		
		conn.close();
	}
}

