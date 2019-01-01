package com.ternary.dao;

import java.util.List;

import com.ternary.model.Request;

public interface RequestDao {

	public List<Request> getRequests(int empId, String status);
}
