package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Employee;
import com.revature.trms.validators.PasswordValidator;

public class ProfileController {

	public static String Profile(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/html/profile.html";
	}

	public static String UpdateProfile(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod()!="POST") {
			return "/html/profile.html";
		}
		
		if(req.getParameter("password") == null || req.getParameter("confirm-password") == null) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: Please enter your password to change your profile details."));
			return "/html/profile.html";
		}
		
		// only triggers if exactly one of the 'new password/new confirmation password' fields are null		
		if(req.getParameter("new-password")== null ^ req.getParameter("new-conf-password") == null) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: New Password and New Password confirmation do not match"));
			return "html/profile/html";
		}
		
		// new password does not match the new confirmation
		if((req.getParameter("new-password") != null) && !req.getParameter("new-password").equals(req.getParameter("new-conf-password"))){
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: New Password and New Password confirmation do not match"));
			return "html/profile/html";
		}
		
		
		// Both password and confirm password are not null at this point.
		if(!req.getParameter("password").equals(req.getParameter("confirm-password"))) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: 'Confirm Password' and 'Password' do not match "));
			return "/html/profile.html";
		}
		
		Employee employee = (Employee) req.getAttribute("Employee");
		if(!LoginController.validCredentials(employee, employee.getUsername(), req.getParameter("password"))) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: Incorrect Password"));
			return "/html/profile.html";
		}
		
		// parse POST data:
		String new_firstname = req.getParameter("emp-first-name");
		String new_lastname = req.getParameter("emp-last-name");
		String new_email = req.getParameter("emp-email");
		String new_password = req.getParameter("new-password");
		
		if(new_firstname == null || new_lastname == null || new_email == null || new_firstname.equals("") || new_lastname.equals("") || new_email.equals("")) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: Missing required field(s)"));
			return "/html/profile.html";
		}
		
		employee.setFirstname(new_firstname);
		employee.setLastname(new_lastname);
		employee.setEmail(new_email);
		
		
		
		// Check that the new password is valid
		if(new_password != null && PasswordValidator.validatePassword(new_password)) {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error: Invalid new password"));
			return "/html/profile.html";
		}
		
		
		String new_password_hashed = BCrypt.hashpw(new_password, BCrypt.gensalt());
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		if( edi.updateEmployee(employee) && edi.updateEmployeePassword(employee.getEmp_id(), new_password_hashed)) {
			
			req.setAttribute("success", "Your profile has been updated.");
			
		}else {
			req.getSession().setAttribute("Alert", new Alert("danger", "Error occurred while trying to update profile information. Please try again later. "));
			
		}
		
		
		
		return "/html/profile.html";
		
	}

}
