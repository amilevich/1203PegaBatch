package com.revature.trms.dao;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.revature.trms.models.Attachment;

public interface AttachmentDAO {

	// CRUD Operations:
	// Create
	public boolean insertAttachment(Attachment attachment, InputStream is);
	
	// Read
	//public Attachment getAttachment(int file_id);

	public Attachment getAttachment(int file_id, HttpServletResponse resp);
	
	// Update
	
	// Delete
	
}
