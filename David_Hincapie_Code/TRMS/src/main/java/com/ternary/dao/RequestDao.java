package com.ternary.dao;

import java.util.List;

import com.ternary.model.Employee;
import com.ternary.model.Request;

public interface RequestDao {

	// CRUD OPERTAIONS
	public int insertCompleteRequest(Request request);

	// create
	// eventlocation, event, grade, request
	public int insertEventLocation(Request request);

	public int insertEvent(Request request, int locationId);

	public int insertGrade(Request request);

	public int insertRequest(Request request, int eventId, int gradeId);

	public int insertAttachment(String fileName, int requestId);

	// select (read)
	public List<Request> getRequests(int empId);

	public Request getRequest(int requestId);

	public List<Request> getMgrRequests(Employee employee);

	// update
	// update request status
	public void cancelRequest(int requestId);

	// delete

}
