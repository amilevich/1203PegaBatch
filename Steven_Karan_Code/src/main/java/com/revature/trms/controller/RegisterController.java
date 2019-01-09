package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.trms.daoimpls.DepartmentDAOImpl;
import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.daoimpls.PositionDAOImpl;
import com.revature.trms.models.Employee;
import com.revature.trms.validators.GeneralValidator;
import com.revature.trms.validators.PasswordValidator;

public class RegisterController {

	public static String Register(HttpServletRequest req) {
		System.out.println("In registration controller main method");
		if (req.getMethod().equals("GET")) {
			return "/html/register.html";
		}

		Employee empl = new Employee();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();

		empl.setFirstname(req.getParameter("firstname"));
		empl.setLastname(req.getParameter("lastname"));
		empl.setUsername(req.getParameter("username"));

		

		empl.setPassword(req.getParameter("password"));

		empl.setEmail(req.getParameter("email"));

		empl.setDepartment(req.getParameter("department"));

		// get position
		empl.setPosition(req.getParameter("position"));

		// get supervisor id
		String super_username = req.getParameter("super_username");
		Employee supervisor = edi.getEmployeeByUsername(super_username);

		// Null check for the supervisor before dereferencing
		if (supervisor != null) {
			empl.setSupervisor_id(supervisor.getEmp_id());
		} else {
			empl.setSupervisor_id(-1);
		}

		// Check to see if employee that is attempting to register is valid before
		// registering them
		if (validNewEmployee(empl)) {
			// valid registration form!
			edi.insertEmployee(empl);
			return "/html/login.html";
		} else {
			// Invalid registration form!
			return "/html/register.html";
		}
	}

	// Should separate each check into its own method. make calls directly from
	// register to avoid unnecessary database calls and can end early.
	private static boolean validNewEmployee(Employee empl) {
		// null check first to avoid nullptrexception
		if (empl == null) {
			return false;
		}

		// username check
		String username = empl.getUsername();
		if (!GeneralValidator.isAlphaNumeric(username) || username.length() > 20 || username.length() < 5) {
			System.out.println("Invalid username");
			return false;
		}

		// password check
		String password = empl.getPassword();
		if (!PasswordValidator.validatePassword(password)) {
			System.out.println("Invalid password");
			return false;
		}else {
			String unhashedpw = empl.getPassword();
			String hashedpw = BCrypt.hashpw(unhashedpw, BCrypt.gensalt());
			empl.setPassword(hashedpw);
		}

		// email check
		String email = empl.getEmail();
		if (!(email.contains("@") && email.contains("."))) {
			System.out.println("Invalid email");
			return false;
		}

		// firstname check
		String firstname = empl.getFirstname();
		if (!GeneralValidator.isSanitized(firstname) || firstname.length() > 20 || firstname.length() < 1) {
			System.out.println("Invalid firstname");
			return false;
		}

		// lastname check
		String lastname = empl.getLastname();
		if (!GeneralValidator.isSanitized(firstname) || lastname.length() > 20 || lastname.length() < 1) {
			System.out.println("Invalid lastname");
			return false;
		}

		// supervisor id check
		// int super_id = empl.getSupervisor_id();
		/*
		 * if (super_id < 1) { System.out.println("Invalid Supervisor id"); return
		 * false; }
		 */

		String dept = empl.getDepartment();
		DepartmentDAOImpl ddi = new DepartmentDAOImpl();
		if (!ddi.dept_exists(dept)) {
			return false;
		}

		String pos = empl.getPosition();
		PositionDAOImpl pdi = new PositionDAOImpl();
		if (!pdi.positionExists(pos)) {
			return false;
		}

		// If none of the checks failed, return true
		return true;
	}

}
