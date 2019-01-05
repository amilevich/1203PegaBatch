package com.ternary.dao;

import java.sql.SQLException;
import java.util.List;

import com.ternary.model.Request;

public interface RequestDao {

	// CRUD OPERTAIONS
	public boolean insertRequest(Request request) throws SQLException;

	public List<Request> getRequests(int empId);
}
