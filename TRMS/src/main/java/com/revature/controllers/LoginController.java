package com.revature.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.revature.bean.Department;
import com.revature.bean.Employee;
import com.revature.bean.Login;
import com.revature.bean.Management;
import com.revature.daoimpl.DepartmentDaoImpl;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.LoginDaoImpl;
import com.revature.daoimpl.ManagementDaoImpl;

public class LoginController {

	public static String Login(HttpServletRequest request) {

		if (request.getMethod().equals("GET")) {
			return "/html/Login.html";
		}

//		 alert(employee.lastName);
//         document.getElementById("firstname").value = employee.firstName;
//         document.getElementById("lastname").value = employee.lastName;
		LoginDaoImpl loginDI = new LoginDaoImpl();
		Login login = new Login();
		EmployeeDaoImpl employDaoImpl = new EmployeeDaoImpl();
		Employee employee = new Employee();
		ManagementDaoImpl managementDaoImpl = new ManagementDaoImpl();
		Management management = new Management();
		DepartmentDaoImpl departmentDaoImpl = new DepartmentDaoImpl();
		Department department = new Department();
		
		login.setUsername(request.getParameter("screenname"));
		login.setPasswd(request.getParameter("passwd"));

		if (loginDI.isInLogin(login)) {
			//sessions persist data beyond the request's lifetime
			request.getSession().setAttribute("Login", login);
			
			try {
				employDaoImpl.selectEmployee(employee, login);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("Employee", employee);
			
			try {
				departmentDaoImpl.selectEmployeeDepartment(department, employee);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.getSession().setAttribute("Department", department);
			
			try {
				managementDaoImpl.employeeManager(management, employee);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("Management", management);
			
			return "/html/EmployeeHome.html";
		}

		else
			try {
				if (loginDI.managementLogin(login)) {
					// sessions persist data beyond the request's lifetime
					request.getSession().setAttribute("Login", login);
					managementDaoImpl.selectManagement(management, login);
					request.getSession().setAttribute("Management", management);
					departmentDaoImpl.selectManagementDepartment(department, management);
					request.getSession().setAttribute("Department", management);
					return "/html/ManagementHome.html";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return "/html/Login.html";
	}
}