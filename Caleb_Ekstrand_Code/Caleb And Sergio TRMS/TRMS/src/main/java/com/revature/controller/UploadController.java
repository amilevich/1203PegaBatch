package com.revature.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.dao.TicketDAOImpl;
import com.revature.dao.UploadImpl;
import com.revature.model.User;


public class UploadController {
	
	public static String downloadFile(HttpServletRequest request) {
			
	        try {
	            // Apache Commons-Fileupload library classe
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload sfu  = new ServletFileUpload(factory);
	
	            if (! ServletFileUpload.isMultipartContent(request)) {
	                System.out.println("sorry. No file uploaded");
	                return "/html/Home.html";
	            }
	            // parse request
	            List items = sfu.parseRequest(request);
	            FileItem  file = (FileItem) items.get(1);
	           	FileItem i = (FileItem) items.get(0);
	           	String id = i.getString();
	           	
	            
	            //upload file
	            UploadImpl upload = new UploadImpl();
	            upload.uploadImage(file, Integer.parseInt(id));
	        }catch(Exception e) {
	           e.printStackTrace();
	        }
	        TicketDAOImpl tdi = new TicketDAOImpl();
	        User user = (User)request.getSession().getAttribute("User");
			user.setTickets(tdi.selectTicketsByUserId(user.getUserId()));
			request.getSession().setAttribute("User", user);
	        return "/html/Home.html";
		}


}
