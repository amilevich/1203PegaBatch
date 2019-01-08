package com.revature.trms.daoimpls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

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
	public Attachment getAttachment(int file_id, HttpServletResponse resp) {
		Attachment attachment = null;
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM attachment WHERE attach_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, file_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				
				attachment = new Attachment(rs.getInt("attach_id"),rs.getInt("reimb_id"), rs.getString("attach_name"),rs.getBlob("attachment"));
				
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getAttach_name() + "\"");

				try (InputStream is = attachment.getAttachment().getBinaryStream(); OutputStream os = resp.getOutputStream();){
					byte[] buffer = new byte[1024];
		             int bytesRead = -1;
		             
		             while ((bytesRead = is.read(buffer)) != -1) {
		                 os.write(buffer, 0, bytesRead);
		             }
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
	             
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return attachment;
	}

}
