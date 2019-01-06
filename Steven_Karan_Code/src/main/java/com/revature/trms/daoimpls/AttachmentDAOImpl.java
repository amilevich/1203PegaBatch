package com.revature.trms.daoimpls;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.AttachmentDAO;
import com.revature.trms.models.Attachment;
import com.revature.trms.util.ConnFactory;

public class AttachmentDAOImpl implements AttachmentDAO {

	private static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public boolean insertAttachment(Attachment attachment, InputStream is) {
		try(Connection conn = cf.getConnection()){
			String sql = "INSERT INTO attachment VALUES(null,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,attachment.getReimb_id());
			ps.setString(2, attachment.getAttach_name());
			ps.setBlob(3, is);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0)
				return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Attachment getAttachment(int file_id) {
		Attachment attachment = null;
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM attchment WHERE attach_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, file_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				attachment = new Attachment(rs.getInt("attach_id"),rs.getInt("reimb_id"), rs.getString("attach_name"),rs.getBlob("attachment"));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return attachment;
	}

}
