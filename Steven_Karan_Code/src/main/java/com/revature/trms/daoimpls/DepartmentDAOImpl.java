package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.DepartmentDAO;
import com.revature.trms.util.ConnFactory;

public class DepartmentDAOImpl implements DepartmentDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean dept_exists(String department) {
		
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM department WHERE dept = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, department);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}

}
