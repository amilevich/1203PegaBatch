package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.trms.dao.SignatureDAO;
import com.revature.trms.models.Event;
import com.revature.trms.models.Signature;
import com.revature.trms.util.ConnFactory;

public class SignatureDAOImpl implements SignatureDAO {
	private static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public boolean insertSignature(Signature sign) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO Signature VALUES(null,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			// Turn off auto-commit
			conn.setAutoCommit(false);
			
			ps.setInt(1, sign.getReimb_id());
			ps.setInt(2, sign.getSign_by_id());
			ps.setDate(3, Date.valueOf(sign.getSign_date()));
			ps.setInt(4, sign.isApproved_by_email() == true ? 1 : 0);
			
			if( ps.executeUpdate() > 0) {
				System.out.println("COMMITTING CHANGES");
				conn.commit();
				return true;
			}
		
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Signature> getAllSignatureByEmp(int emp) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM Signature WHERE sign_by = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp);
			ResultSet rs = ps.executeQuery();

			ArrayList<Signature> sign_list = new ArrayList<>();
			while (rs.next()) {
				Signature sign = new Signature();
				sign.setId(rs.getInt("sign_id"));
				sign.setReimb_id(rs.getInt("reimb_id"));
				sign.setSign_by_id(emp);
				sign.setSign_date(rs.getDate("sign_date").toLocalDate());
				sign.setApproved_by_email(rs.getInt("approved_by_email") == 1 ? true : false);
				sign_list.add(sign);
			}
			return sign_list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Signature> getAllSignatureByReimb(int reimb) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM Signature WHERE sign_by = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb);
			ResultSet rs = ps.executeQuery();

			ArrayList<Signature> sign_list = new ArrayList<>();
			while (rs.next()) {
				Signature sign = new Signature();
				sign.setId(rs.getInt("sign_id"));
				sign.setReimb_id(reimb);
				sign.setSign_by_id(rs.getInt("sign_by_id"));
				sign.setSign_date(rs.getDate("sign_date").toLocalDate());
				sign.setApproved_by_email(rs.getInt("approved_by_email") == 1 ? true : false);
				sign_list.add(sign);
			}
			return sign_list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateSignature(Signature sign) {
		try (Connection conn = cf.getConnection();) {
			 
			String sql = "UPDATE Signature SET reimb_id=?, sign_by=?, sign_date=?, approved_by_email=? WHERE event_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(sign);
			ps.setInt(1, sign.getReimb_id());
			ps.setInt(1, sign.getSign_by_id());
			ps.setDate(2, Date.valueOf(sign.getSign_date()));
			ps.setInt(4, sign.isApproved_by_email() ? 1 : 0);
			// Note: at most 1 row can be updated at a time given that the where clause selects an id
			if ( ps.executeUpdate() >= 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteSignature(int id) {
		
		return false;
	}
		
}
