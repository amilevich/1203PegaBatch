package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.PositionDAO;
import com.revature.trms.util.ConnFactory;

public class PositionDAOImpl implements PositionDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean positionExists(String position) {
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM position WHERE pos = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, position);
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
