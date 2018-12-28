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
	public int getDepartmentID(String deptName) {
		
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT dept_id FROM department WHERE dept_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deptName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("dept_id");
			}else {
				// 0 indicates that the query was successful but the department could not be found
				return 0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// -1 indicates that the query was unsuccessful query overall
		return -1;
	}

}
