package com.revature.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Attachment;
import com.revature.util.ConnFactory;

public class DownloadDAOImpl implements DownloadDAO {
	
	public final static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public Attachment getBlob(int ticketId) {
		Attachment data = null;
		try(Connection con = cf.getConnection()){
			PreparedStatement ps = con.prepareStatement("select attatchment from attatchment_db where ticket_id = ?");
			ps.setInt(1, ticketId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Blob blob = rs.getBlob("attatchment");
				data = new Attachment(blob);
				
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
