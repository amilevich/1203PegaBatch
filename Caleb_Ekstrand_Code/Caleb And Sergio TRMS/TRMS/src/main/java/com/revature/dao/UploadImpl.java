package com.revature.dao;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;

import com.revature.model.Attachment;

import com.revature.util.ConnFactory;


public class UploadImpl implements UploadDAO{
	
	public final static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void uploadImage(FileItem file, int ticketId) {
        // Connect to Oracle
		try(Connection con = cf.getConnection()){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into attatchment_db values(?, ?)");
        // size must be converted to int otherwise it results in error
			ps.setInt(1, ticketId);
			ps.setBinaryStream(2, file.getInputStream(), (int) file.getSize());
			ps.executeUpdate();
			con.commit();
			con.close();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
