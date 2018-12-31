package com.trms.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.model.Employee2;

public class IndexController {

	public static String Index(HttpServletRequest request) {
		
		return null;
	}

	public static String Employee2JSON(HttpServletRequest request, HttpServletResponse response) {
		Employee2 employee2 = (Employee2)request.getSession().getAttribute("employee2");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(employee2));
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
