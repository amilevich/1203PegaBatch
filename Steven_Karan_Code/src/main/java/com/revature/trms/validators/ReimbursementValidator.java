package com.revature.trms.validators;


import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.daoimpls.EventDAOImpl;
import com.revature.trms.daoimpls.ReimbursementStatusDAOImpl;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;

public class ReimbursementValidator {
	
	
	
	public static boolean validate_Reimbursement(Reimbursement reimb) {
		
		// null check to begin with to avoid nullptrexceptions later
		if(reimb == null) {
			System.out.println("1");
			return false;
		}
		
		// validate emp_id
		int emp_id = reimb.getEmployee().getEmp_id();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		if(edi.getEmployeeByID(emp_id)==null) {
			System.out.println("2");
			return false;
		}
		
		// validate event and event_id
		Event event = reimb.getEvent();
		
		if(event == null) {
			System.out.println("3");
			return false;
		}
//		if(new EventDAOImpl().getEvent(event.getEvent_id()) == null) {
//			System.out.println("4");
//			return false;
//		}
		
		// validate status_id
//		if(!(new ReimbursementStatusDAOImpl().statusExists(reimb.getStatus_id()))) {
//			return false;
//		}
		
		// validate justification
		if(reimb.getJustification()==null || reimb.getJustification().length() > 1000) {
			System.out.println("5");
			return false;
		}
		
		// validate work_time
		if( reimb.getWork_time_missed() < 0) {
			System.out.println("6");
			return false;
		}
		
		// all validations passed:
		return true;
	}
	
}
