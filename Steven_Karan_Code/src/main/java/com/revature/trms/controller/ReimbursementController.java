package com.revature.trms.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import com.revature.trms.daoimpls.ReimbursementDAOImpl;
import com.revature.trms.models.Address;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Employee;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.validators.AddressValidator;
import com.revature.trms.validators.EventValidator;
import com.revature.trms.validators.ReimbursementValidator;

public class ReimbursementController {

	
	public static String Reimburse(HttpServletRequest req) {
		System.out.println("Processing Reimbursement");
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		Alert alert = null;
		
		// Check if user is authenticated:
		if(emp==null) {
			System.out.println("Could not find employee session var");
			return "/html/index.html";
		}
		
		if (req.getMethod().equals("GET")) {
			System.out.println("GET method");
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
		System.out.println(addr);
		// validate address, only continue if valid
		
		// Note: Temporarily, same thing is done for every failed validator (returning the page)
		// Can be changed to inform the user of the specific issue with the form
		if(!AddressValidator.validate_Address(addr)) {
			//System.out.println("Invalid address");
			alert = new Alert("danger","Error: Invalid Address");
			req.getSession().setAttribute("Alert", alert);
			return "/html/reimburse.html";
		}
		
		
		Event event = new Event();
		event.setType_name(req.getParameter("event-type"));
		
		System.out.println(req.getParameter("event-date"));
		Date event_date = Date.valueOf(req.getParameter("event-date"));
		event.setStart_date(event_date.toLocalDate());
		
		
		System.out.println(req.getParameter("event-time"));
		String event_date_time_str = req.getParameter("event-date") + " " + req.getParameter("event-time");
		System.out.println(event_date_time_str);
		
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
		reimb.setEmp_id(emp.getEmp_id());
		// Setting request_date to today:
		reimb.setRequest_date(LocalDate.now());
		reimb.setJustification(req.getParameter("justification"));
		reimb.setWork_time_missed( Integer.parseInt(req.getParameter("work-missed")) );
		
		
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
		
		if(success) {
			alert = new Alert("success", "Reimbursement Submitted!");
		}else {
			alert = new Alert("danger", "Error trying to submit reimbursement. Please try again later.");
		}
		req.getSession().setAttribute("Alert", alert);
		return "/html/reimburse.html";
		
		
	}
}
