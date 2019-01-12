package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Login;

public class HomeController {
	
	public static String Home(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String LoginJSON(HttpServletRequest request, HttpServletResponse response) {
		Login login = (Login)request.getSession().getAttribute("Login");
		// ObjectMapper converts an object to a string
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(login));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
