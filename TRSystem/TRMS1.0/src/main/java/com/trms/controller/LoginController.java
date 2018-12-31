package com.trms.controller;
import javax.servlet.http.HttpServletRequest;
import com.trms.dao.Employee2DaoImpl;
import com.trms.model.Employee2;

public class LoginController {

	public static String Login(HttpServletRequest request) {
		
		if(request.getMethod().equals("GET")) {
			return "/html/login.html";
		}
		
		String screenname = request.getParameter("screenname");
		String passwd = request.getParameter("passwd");
		
		Employee2DaoImpl employee2DaoImpl = new Employee2DaoImpl();
		
		Employee2 employee2 = new Employee2();
		
		employee2 = employee2DaoImpl.selectEmployee2ByScreenname(screenname);
		System.out.println(employee2);
		
		
		if(screenname.equals(employee2.getScreenname()) && passwd.equals(employee2.getPassword())) {
			request.getSession().setAttribute("Employee", employee2);
			return "/html/index.html";
		}
		else {
			return "/html/login.html";
		}
				
	}

}
