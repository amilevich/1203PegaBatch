package com.trms.controller;
import javax.servlet.http.HttpServletRequest;
import com.trms.dao.Employee2DaoImpl;
import com.trms.model.Employee2;

public class RegisterController {

	public static String Register(HttpServletRequest request) {
		
		String screenname = request.getParameter("screenname");
		String passwd = request.getParameter("passwd");
		
		Employee2 employee2 = new Employee2();
		
		employee2.setScreenname(screenname);
		employee2.setPassword(passwd);
		
		Employee2DaoImpl employee2DaoImpl = new Employee2DaoImpl();
		employee2DaoImpl.insertEmployee2(employee2);
		
		return "/html/register.html";
	}
}
