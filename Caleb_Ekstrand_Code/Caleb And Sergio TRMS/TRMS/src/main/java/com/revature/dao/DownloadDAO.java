package com.revature.dao;

import com.revature.model.Attachment;

public interface DownloadDAO {
	
	public Attachment getBlob(int ticketId);

}
