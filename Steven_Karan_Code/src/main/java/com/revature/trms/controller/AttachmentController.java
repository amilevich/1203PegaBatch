package com.revature.trms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.trms.daoimpls.AttachmentDAOImpl;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Attachment;

public class AttachmentController {

	
	public static boolean UploadFiles(HttpServletRequest req, int reimbursement_id) throws IOException, ServletException {
		
		Enumeration e = req.getParameterNames();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
		
		ArrayList<Part> files = new ArrayList<>(req.getParts());
	
		
		if(files.size() == 0) {
			System.out.println("file field null");
			Alert alert = new Alert("danger", "Error uploading files specified");
			req.getSession().setAttribute("Alert", alert);
			return false;
		}
		
		files.forEach(file -> {
			Attachment attachment = new Attachment();	
			attachment.setAttach_name(file.getSubmittedFileName());
			
			attachment.setReimb_id(reimbursement_id);
			AttachmentDAOImpl adi = new AttachmentDAOImpl();
			try {
				adi.insertAttachment(attachment, file.getInputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		
		
		
		return true;
	}
	
}
