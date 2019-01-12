package com.revature.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Application;
import com.revature.daoimpl.ApplicationDaoImpl;

public class CandidatesController {

	public static String allReimbursementForms() {
		
		List<Application> appList = new ArrayList<>();
		ApplicationDaoImpl appDI = new ApplicationDaoImpl();
		
		try {
			appList = appDI.selectAppInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
