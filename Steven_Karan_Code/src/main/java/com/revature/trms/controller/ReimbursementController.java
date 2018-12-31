package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.trms.models.Address;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.validators.AddressValidator;
import com.revature.trms.validators.EventValidator;
import com.revature.trms.validators.ReimbursementValidator;

public class ReimbursementController {

	
	public static String Reimburse(HttpServletRequest req) {
		
		if (req.getMethod().equals("GET")) {
			return "/html/reimburse.html";
		}
		
		// if the response is a POST, parse the incoming reimbursement form
		
		// Parse address of event, then event, then remaining reimbursement form fields
		Address addr = new Address();
		addr.setAddress_text(req.getParameter("event-address"));
		addr.setStreet_number(req.getParameter("event-address-street-num"));
		addr.setRoute(req.getParameter("event-address-street"));
		addr.setCity(req.getParameter("event-address-city"));
		addr.setZipcode(req.getParameter("event-address-zip"));
		addr.setState(req.getParameter("event-address-state"));
		addr.setCountry(req.getParameter("event-address-country"));
		
		// validate address, only continue if valid
		
		// Note: Temporarily, same thing is done for every failed validator (returning the page)
		// Can be changed to inform the user of the specific issue with the form
		if(!AddressValidator.validate_Address(addr)) {
			return "/html/reimburse.html";
		}
		
		Event event = new Event();
		
		
		if(!EventValidator.validate_Event(event)) {
			return "/html/reimburse.html";
		}
		
		Reimbursement reimb = new Reimbursement();
		
		
		if(!ReimbursementValidator.validate_Reimbursement(reimb)) {
			return "/html/reimburse.html";
		}
		
		// All validators passed, can move forward with inserting the form into the database
		
		
		return "/html/reimburse.html";
		
		
	}
}
