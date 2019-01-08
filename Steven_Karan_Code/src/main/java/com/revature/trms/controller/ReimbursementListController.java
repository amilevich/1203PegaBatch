package com.revature.trms.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.daoimpls.EventDAOImpl;
import com.revature.trms.daoimpls.ReimbursementDAOImpl;
import com.revature.trms.daoimpls.ReimbursementStatusDAOImpl;
import com.revature.trms.daoimpls.SignatureDAOImpl;
import com.revature.trms.models.Employee;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.models.Signature;

public class ReimbursementListController {

	public static String ReimbursementList(HttpServletRequest req) {
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		// Ensure that user is authenticated...
		System.out.println(emp);
		if (emp == null) {
			return "/html/login.html";
		}
		return "/html/reimburse-list.html";
	}

	public static String PersonalReimbursementListJSON(HttpServletRequest req, HttpServletResponse resp) {
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		if (emp != null) {
			System.out.println("Processing Personal Reimbursement List");
			try {
				ArrayList<Reimbursement> reimb_list = new ReimbursementDAOImpl()
						.getAllReimbursementByEmployee(emp.getEmp_id());
				System.out.println(reimb_list);
				resp.getWriter().write(new ObjectMapper().writeValueAsString(reimb_list));
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String AssignReimbursementListJSON(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Processing Assigned Reimbursement List");
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		if (emp != null) {
			try {
				ArrayList<Reimbursement> reimb_list = new ArrayList<>();
				if (emp.getPosition().equals("Benifits Coordinator")) {
					reimb_list = new ReimbursementDAOImpl().getAllReimbursementForBenco();
				} else {
					reimb_list = new ReimbursementDAOImpl().getAllReimbursementByNext(emp.getEmp_id());
				}
				resp.getWriter().write(new ObjectMapper().writeValueAsString(reimb_list));
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String FindNext(HttpServletRequest req) {

		return "/html/signature.html";
	}

	public static String SelectAction(HttpServletRequest req) {
		System.out.println("In select Action");
		Employee emp = (Employee) req.getSession().getAttribute("Employee");

		String action = req.getParameter("action");
		int reimb_id = Integer.parseInt(req.getParameter("reimb-id"));
		Reimbursement reimb = new ReimbursementDAOImpl().getReimbursement(reimb_id);
		int emp_id = emp.getEmp_id();

		switch (action) {
		case "approve":// approve reimbursement application

			Signature sign = new Signature(reimb_id, emp_id, LocalDate.now(), false);
			new SignatureDAOImpl().insertSignature(sign);

			if (!reimb.isSup_flag()) { // check if supervisor has signed
				reimb.setSup_flag(true);
				reimb.setStatus_name("Pending Department Head Approval");
				if (!reimb.isDept_flag()) {
					String dept = req.getParameter("department");
					Employee deptHead = new EmployeeDAOImpl().getDepartmentHead(dept);
					reimb.setNext_id(deptHead.getEmp_id());
					if (deptHead.getEmp_id() == emp.getEmp_id()) {
						Signature signCopy = new Signature(reimb_id, deptHead.getEmp_id(), LocalDate.now(), false);
						new SignatureDAOImpl().insertSignature(signCopy);
						reimb.setDept_flag(true);
						reimb.setNext_id(0);
					}
				}
			} else if (!reimb.isDept_flag()) {
				reimb.setDept_flag(true);
				reimb.setStatus_name("Pending Benifits Coordinator Approval");
				reimb.setNext_id(0);

			} else if (!reimb.isBenco_flag()) {
				reimb.setBenco_flag(true);
				reimb.setStatus_name("Pending Employee Grading/Presentation");
				reimb.setNext_id(reimb.getEmployee().getEmp_id());
			}
			new ReimbursementStatusDAOImpl().updateReimbursementStatus(reimb);
			break;
		case "deny":// deny reimbursement application

			break;
		case "confirm":// confirm reimbursement application
			sign = new Signature(reimb_id, emp_id, LocalDate.now(), false);
			new SignatureDAOImpl().insertSignature(sign);
			if (!reimb.isSup_flag()) {
				reimb.setSup_flag(true);
				reimb.setStatus_name("Pending Benifits Coordinator Confirmation");
				reimb.setNext_id(0);

			} else if (!reimb.isBenco_flag()) {
				reimb.setBenco_flag(true);
				reimb.setStatus_name("Funds Awarded");
				reimb.setNext_id(0);
			}
			new ReimbursementStatusDAOImpl().updateReimbursementStatus(reimb);
			break;
			
		case "petition":// request for additional information
			break;
			
		case "grade":// upload presentation or grade
			String g_received = req.getParameter("passing-grade");
			if (g_received != null) {
				reimb.getEvent().setGrade_received(g_received);
				new EventDAOImpl().updateEvent(reimb.getEvent());
				reimb.setStatus_name("Pending Direct Supervisor Confirmation");
				reimb.setSup_flag(false);
				reimb.setBenco_flag(false);
				reimb.setNext_id(emp.getSupervisor_id());
				new ReimbursementStatusDAOImpl().updateReimbursementStatus(reimb);
			}

			break;
		case "info":// send additional information
			break;
		case "change":// change reimbursement amount
			break;
		}
		return "/html/signature.html/";
	}
}
