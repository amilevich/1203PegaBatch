package com.ternary.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.ternary.daoimpl.RequestDaoImpl;
import com.ternary.model.Employee;
import com.ternary.model.Request;

public class ReimbursementController {

	public static String ReimbursementJSON(HttpServletRequest request) {

		RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
		Employee employee = new Employee();
		Request reimbursementRequest = new Request();

		try {
			employee = (Employee) request.getSession().getAttribute("Employee");
			reimbursementRequest.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
			reimbursementRequest
					.setRequestCompleted((Date) new SimpleDateFormat("dd/MM/yyyy").parse("reimbursementDate"));
			reimbursementRequest.setStatus("Created");
			reimbursementRequest.setMoreInfo(false);
			reimbursementRequest.setJustification(request.getParameter("workJustification"));
			reimbursementRequest.setDirectMgrApprovalId(employee.getReportTo());
			reimbursementRequest.setDeptHeadApprovalId(employee.getDepartmentHeadId());
			reimbursementRequest.setBencoApproval(0);
			reimbursementRequest.setDenied(false);
			reimbursementRequest.setDeniedReason("");
			switch (Integer.parseInt(request.getParameter("preApproval"))) {
			case 0:
				reimbursementRequest.setPreApprovedSupervisorId(0);
				break;
			case 1:
				reimbursementRequest.setPreApprovedSupervisorId(employee.getReportTo());
				break;
			case 2:
				reimbursementRequest.setPreApprovedSupervisorId(employee.getDepartmentHeadId());
				break;
			}
			// reimbursementRequest.setApprovalAttachment();
			reimbursementRequest.setEventCost(Double.parseDouble(request.getParameter("cost")));
			reimbursementRequest.setProjectedReimbursement(Double.parseDouble(request.getParameter("reimbursementCoverage")));
			reimbursementRequest.setAwardChanged(false);

			// reimbursementRequest.setExceedAvailable(false);
			reimbursementRequest.setPassingGrade(request.getParameter("gradeCutoff"));
			reimbursementRequest.setFinalGrade("");
			reimbursementRequest.setUploadedPresentation(false);
			// reimbursementRequest.setPresentationAttachment();
			reimbursementRequest.setEventDescription(request.getParameter("eventDescription"));

			// reimbursementRequest.setEventStart();
			// reimbursementRequest.setEventEnd();
			// reimbursementRequest.setReimbCoverage();

			reimbursementRequest.setStreetAddress(request.getParameter("streetAddress"));
			reimbursementRequest.setCity(request.getParameter("city"));
			reimbursementRequest.setState(request.getParameter("state"));
			reimbursementRequest.setZipCode(request.getParameter("zip"));
			reimbursementRequest.setCountry(request.getParameter("country"));

			requestDaoImpl.insertRequest(reimbursementRequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/html/home.html";

	}

}
