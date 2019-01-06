package com.ternary.controller;

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
			reimbursementRequest.setReimbursementDate(new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("reimbursementDate")).getTime()).toLocalDateTime().toLocalDate());
			reimbursementRequest.setEventTime(request.getParameter("eventTime"));
			reimbursementRequest.setStatus("Created");
			reimbursementRequest.setMoreInfo(false);
			reimbursementRequest.setJustification(request.getParameter("workJustification"));
			reimbursementRequest.setDirectMgrApprovalId(employee.getReportTo());
			reimbursementRequest.setDeptHeadApprovalId(employee.getDepartmentHeadId());
			reimbursementRequest.setBencoApprovalId(0);
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
			if (request.getParameter("cost").isEmpty()) {
				reimbursementRequest.setEventCost(0);
			} else {
				reimbursementRequest.setEventCost(Double.parseDouble(request.getParameter("cost")));
			}

			if (request.getParameter("cost").isEmpty()) {
				reimbursementRequest.setProjectedReimbursement(0);
			} else {
				reimbursementRequest
						.setProjectedReimbursement(Double.parseDouble(request.getParameter("projectedReimbursement")));
			}

			reimbursementRequest.setAwardChanged(false);
			reimbursementRequest.setExceedAvailable(false);

			reimbursementRequest.setGradeTypeId(Integer.parseInt(request.getParameter("gradeFormat")));

			switch (Integer.parseInt(request.getParameter("gradeFormat"))) {
			case 1:
				reimbursementRequest.setGradeType("Grade");
				break;
			case 2:
				reimbursementRequest.setGradeType("Percentage");
				break;
			case 3:
				reimbursementRequest.setGradeType("Pass/Fail");
				break;
			case 4:
				reimbursementRequest.setGradeType("Presentation");
				break;

			default:
				break;
			}
			reimbursementRequest.setPassingGrade(request.getParameter("gradeCutoff"));
			reimbursementRequest.setFinalGrade("");
			reimbursementRequest.setUploadedPresentation(false);
			// reimbursementRequest.setPresentationAttachment();
			reimbursementRequest.setEventDescription(request.getParameter("eventDescription"));
			reimbursementRequest.setEventTime(request.getParameter("eventTime"));
			reimbursementRequest.setEventStart(new java.sql.Timestamp(
					new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")).getTime())
							.toLocalDateTime().toLocalDate());
			reimbursementRequest.setEventEnd(new java.sql.Timestamp(
					new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")).getTime())
							.toLocalDateTime().toLocalDate());

			switch (Integer.parseInt(request.getParameter("eventType"))) {
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

			reimbursementRequest.setEventType(request.getParameter("eventType"));
			reimbursementRequest.setExceedAvailibleComment("");

			reimbursementRequest.setStreetAddress(request.getParameter("streetAddress"));
			reimbursementRequest.setCity(request.getParameter("city"));
			reimbursementRequest.setState(request.getParameter("state"));
			reimbursementRequest.setZipCode(request.getParameter("zip"));
			reimbursementRequest.setCountry(request.getParameter("country"));

			reimbursementRequest.setRequestId(requestDaoImpl.insertCompleteRequest(reimbursementRequest));
			System.out.println(reimbursementRequest.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "/html/home.do";

	}

}
