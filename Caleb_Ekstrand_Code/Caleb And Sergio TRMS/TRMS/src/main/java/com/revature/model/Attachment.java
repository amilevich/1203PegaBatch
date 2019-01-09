package com.revature.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class Attachment {
	
	private int ticket_id;
	private String aBase64;
	private byte[] byteImage;

	public Attachment() {}
	
	public Attachment(byte[] bytes) {
		this.byteImage = bytes;
	}

	public Attachment(Blob blob) {
		try {
			int blobLength = (int) blob.length();  
			byte[] bytes = blob.getBytes(1, blobLength);
			this.byteImage = bytes;
			//this.aBase64 = "\"data:image/jpeg;charset=UTF-8;base64," + DatatypeConverter.printBase64Binary(bytes);
			this.aBase64 = "data:image/jpeg;base64," + DatatypeConverter.printBase64Binary(bytes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getaBase64() {
		return aBase64;
	}

	public void setaBase64(String aBase64) {
		this.aBase64 = aBase64;
	}
	
	

	

}
