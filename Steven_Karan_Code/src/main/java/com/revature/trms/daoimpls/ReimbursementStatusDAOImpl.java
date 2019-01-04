package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.ReimbursementStatusDAO;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.util.ConnFactory;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO {

	private static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public boolean insertReimbursementStatus(Reimbursement reimb) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO reimbursement_status VALUES(null,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(2, reimb.getReimb_id());
			ps.setString(3, reimb.getStatus_name());
			ps.setInt(4, reimb.isUrgent() ? 1 : 0);
			ps.setInt(5, reimb.isSup_flag() ? 1 : 0);
			ps.setInt(6, reimb.isDept_flag() ? 1 : 0);
			ps.setInt(7, reimb.isBenco_flag() ? 1 : 0);
			ps.setInt(8, reimb.getNext_id());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateReimbursementStatus(Reimbursement reimb) {
		
		return false;
	}
	
	@Override
	public boolean statusExists(int id) {
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM reimbursement_status WHERE status_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
