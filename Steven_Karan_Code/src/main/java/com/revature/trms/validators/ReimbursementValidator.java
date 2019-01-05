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
			return false;
		}
		
		// validate emp_id
		int emp_id = reimb.getEmployee().getEmp_id();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		if(edi.getEmployeeByID(emp_id)==null) {
			return false;
		}
		
		// validate event and event_id
		Event event = reimb.getEvent();
		
		if(event == null) {
			return false;
		}
		if(new EventDAOImpl().getEvent(event.getEvent_id()) == null) {
			return false;
		}
		
		// validate status_id
		if(!(new ReimbursementStatusDAOImpl().statusExists(reimb.getStatus_id()))) {
			return false;
		}
		
		// validate justification
		if(reimb.getJustification()==null || reimb.getJustification().length() < 1000) {
			return false;
		}
		
		// validate work_time
		if( reimb.getWork_time_missed() < 0) {
			return false;
		}
		
		// all validations passed:
		return true;
	}
	
}
