package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.trms.daoimpls.AddressDAOImpl;
import com.revature.trms.models.Address;
import com.revature.trms.validators.AddressValidator;

public class ReimbursementController {

	
	public static String Reimburse(HttpServletRequest req) {
		System.out.println("Processing reimbursement");
		
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
		System.out.println(addr);
		// validate address, only continue if valid
		
		// Note: Temporarily, same thing is done for every failed validator (returning the page)
		// Can be changed to inform the user of the specific issue with the form
		if(!AddressValidator.validate_Address(addr)) {
			System.out.println("Invalid address");
			return "/html/reimburse.html";
		}
		
		// insert address into database:
		AddressDAOImpl adi = new AddressDAOImpl();
		if( adi.insertAddress(addr) ) {
			
			int address_id = adi.getAddressId(addr);
			System.out.println(address_id);
			
			return "/html/index.html";
		}
		
		
		
		/*Event event = new Event();
		//event.setCoverage(req.get);
		event.setDescription(req.getParameter("description"));
		event.setFormat_name(req.getParameter("grade-format"));
		
		String event_date_str = req.getParameter("event-date");
		LocalDate event_date = LocalDate.parse(event_date_str);
		
		
		
		
		
		event.setLocation(addr);
		
		
		if(!EventValidator.validate_Event(event)) {
			return "/html/reimburse.html";
		}
		
		Reimbursement reimb = new Reimbursement();
		
		
		if(!ReimbursementValidator.validate_Reimbursement(reimb)) {
			return "/html/reimburse.html";
		}*/
		
		// All validators passed, can move forward with inserting the form into the database
		
		
		return "/html/reimburse.html";
		
		
	}
}
