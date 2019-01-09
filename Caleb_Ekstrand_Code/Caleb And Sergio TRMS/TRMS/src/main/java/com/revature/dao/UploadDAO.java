package com.revature.dao;

import org.apache.commons.fileupload.FileItem;

import com.revature.model.Attachment;

public interface UploadDAO {
	
	public void uploadImage(FileItem file, int ticketId);
	
}
