package com.revature.trms.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.trms.daoimpls.AttachmentDAOImpl;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Attachment;

public class AttachmentController {

	
	public static String UploadFile(HttpServletRequest req) throws IOException, ServletException {
		System.out.println("UPLOADING FILE");
		if(req.getMethod().equals("GET")) {
			System.out.println("GET GOT");
			return "/html/index.html";
		}
		
		Enumeration e = req.getParameterNames();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}

		Part fileField = req.getPart("file");
		
		if(fileField == null) {
			System.out.println("file field null");
			Alert alert = new Alert("danger", "Error uploading file specified");
			req.getSession().setAttribute("Alert", alert);
			return "/html/index.html";
		}
		
		String filename = fileField.getSubmittedFileName();
		
		if(filename == null) {
			System.out.println("filename null");
			Alert alert = new Alert("danger", "Error: Invalid File Name");
			req.getSession().setAttribute("Alert", alert);
			return "/html/index.html";
			
		}
		
		String description = "";
		int reimb_id = 102;
		Attachment attachment = new Attachment(); 
		
		attachment.setAttach_name(filename);
		attachment.setDescription(description);
		attachment.setReimb_id(reimb_id);
		
		AttachmentDAOImpl adi = new AttachmentDAOImpl();
		adi.insertAttachment(attachment, fileField.getInputStream());
		
		System.out.println("did it!");
		
		return "/html/index.html";
	}
	
}
