package com.revature.trms.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.revature.trms.daoimpls.ReimbursementDAOImpl;
import com.revature.trms.models.Address;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Employee;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.validators.AddressValidator;
import com.revature.trms.validators.EventValidator;
import com.revature.trms.validators.GeneralValidator;
import com.revature.trms.validators.ReimbursementValidator;

public class ReimbursementController {

	
	public static String Reimburse(HttpServletRequest req) throws IOException, ServletException {
		System.out.println("Processing Reimbursement");
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		Alert alert = null;
		
		// Check if user is authenticated, if not redirect them to the home page:
		if(emp==null) {
			return "/html/index.html";
		}
		
		if (req.getMethod().equals("GET")) {
			return "/html/reimburse.html";
		}
		
		
		// if the response is a POST, parse the incoming reimbursement form
		// Parse address of event, then event, then remaining reimbursement form fields
		Address addr = new Address();
		addr.setAddress_text(req.getParameter("event-location"));
		addr.setStreet_number(req.getParameter("street-num"));
		addr.setRoute(req.getParameter("event-address-street"));
		addr.setCity(req.getParameter("event-address-city"));
		addr.setState(req.getParameter("event-address-state"));
		addr.setZipcode(req.getParameter("event-address-zip"));
		addr.setCountry(req.getParameter("event-address-country"));
		
		// validate address, only continue if valid
		
		// Note: Temporarily, same thing is done for every failed validator (returning the page)
		// Can be changed to inform the user of the specific issue with the form
		if(!AddressValidator.validate_Address(addr)) {
			//System.out.println("Invalid address");
			alert = new Alert("danger","Error: Invalid Address\nPlease fill in all address fields.");
			
			req.getSession().setAttribute("Alert", alert);
			return "/html/reimburse.html";
		}
		
		
		Event event = new Event();
		event.setType_name(req.getParameter("event-type"));
		Date event_date = Date.valueOf(req.getParameter("event-date"));
		event.setStart_date(event_date.toLocalDate());
		String event_date_time_str = req.getParameter("event-date") + " " + req.getParameter("event-time");
		
		String pattern = "yyyy-MM-dd hh:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		java.util.Date date = null;
		try {
			date = simpleDateFormat.parse(event_date_time_str);
		} catch (ParseException e) {
			e.printStackTrace();
			return "/html/reimburse.html";
		}
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		event.setStart_time(timestamp);
		event.setDescription(req.getParameter("description"));
		event.setFormat_name(req.getParameter("grade-format"));
		event.setPassing_grade(req.getParameter("passing-grade"));
		event.setCost(Integer.parseInt(req.getParameter("event-cost")));
		event.setGrade_received(null);
		
		// linking address and event:
		event.setLocation(addr);
		
		if(!EventValidator.validate_Event(event)) {
			//System.out.println("Invalid event");
			alert = new Alert("danger","Error: Invalid Event");
			req.getSession().setAttribute("Alert", alert);
			return "/html/reimburse.html";
		}
		
		
		Reimbursement reimb = new Reimbursement();
		reimb.setStatus_id(0);
		reimb.setEmployee(emp);
		// Setting request_date to today:
		reimb.setRequest_date(LocalDate.now());
		reimb.setJustification(req.getParameter("justification"));
		
		String work_missed_str = req.getParameter("work-missed");
		System.out.println("Before numeric validation. Work Missed: " + work_missed_str);
		if(GeneralValidator.isNumeric(work_missed_str)) {
			System.out.println("valid");
			reimb.setWork_time_missed( Integer.parseInt(work_missed_str ));
		}else {
			System.out.println("invalid");
			alert = new Alert("danger", "Error: Invalid work time missed.\nOnly numbers are allowed in this field");
			req.getSession().setAttribute("Alert",alert);
		}
		
		
		if(!ReimbursementValidator.validate_Reimbursement(reimb)) {
			alert = new Alert("danger","Error: Invalid Reimbursement Details");
			req.getSession().setAttribute("Alert", alert);
			return "/html/reimburse.html";
		}
		
		// linking event and reimbursement (address transitively)
		reimb.setEvent(event);
		
		// All validators passed, can move forward with inserting the form into the database
		ReimbursementDAOImpl rdi = new ReimbursementDAOImpl();
		boolean success = rdi.insertReimbursement(reimb);
		
		if(success && reimb.getReimb_id()>0) {
			
			// Attach files 
			AttachmentController.UploadFiles(req, reimb.getReimb_id());
			
			
			alert = new Alert("success", "Reimbursement Submitted!");
		}else {
			alert = new Alert("danger", "Error trying to submit reimbursement. Please try again later.");
		}
		req.getSession().setAttribute("Alert", alert);
		return "/html/reimburse.html";
		
		
	}
}
