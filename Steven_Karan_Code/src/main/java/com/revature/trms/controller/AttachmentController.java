package com.revature.trms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.trms.daoimpls.AttachmentDAOImpl;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Attachment;

public class AttachmentController {

	
	public static boolean UploadFiles(HttpServletRequest req, int reimbursement_id) throws IOException, ServletException {
		
		
		
		ArrayList<Part> files = new ArrayList<>(req.getParts());
		boolean hasFiles = false;
		
		for(int i = 0; i < files.size(); i++) {
			if(files.get(i)!= null && files.get(i).getContentType()!= null && !files.get(i).getSubmittedFileName().equals("")) {
				hasFiles = true;
				break;
			}
		}
		
		
		if(!hasFiles) {
			return true;
		}
		
		if(files.size() == 0) {
			
			Alert alert = new Alert("danger", "Error uploading files specified");
			req.getSession().setAttribute("Alert", alert);
			return false;
		}
		
		files.forEach(file -> {
			if(file == null || file.getContentType()== null || file.getSubmittedFileName().equals("")) {
				return;
			}
			
			Attachment attachment = new Attachment();	
			attachment.setAttach_name(file.getSubmittedFileName());
			
			attachment.setReimb_id(reimbursement_id);
			AttachmentDAOImpl adi = new AttachmentDAOImpl();
			try {
				adi.insertAttachment(attachment, file.getInputStream());
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
		});
		return true;
	}

	public static String DownloadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
            AttachmentDAOImpl adi = new AttachmentDAOImpl();
            int id = Integer.parseInt(req.getParameter("file-id"));
     		Attachment attachment = adi.getAttachment(id, resp);
		
		return null;
	}
	
}
