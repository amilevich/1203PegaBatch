package com.assignment.bean;

public class AdditionalInformation {
	private int ai_id,rei_id, from_id, to_id, state;
	String request, response;
	
	public AdditionalInformation(int ai_id, int rei_id, int from_id, int to_id, int state, String request,
			String response) {
		super();
		this.ai_id = ai_id;
		this.rei_id = rei_id;
		this.from_id = from_id;
		this.to_id = to_id;
		this.state = state;
		this.request = request;
		this.response = response;
	}

	public int getAi_id() {
		return ai_id;
	}

	public void setAi_id(int ai_id) {
		this.ai_id = ai_id;
	}

	public int getRei_id() {
		return rei_id;
	}

	public void setRei_id(int rei_id) {
		this.rei_id = rei_id;
	}

	public int getFrom_id() {
		return from_id;
	}

	public void setFrom_id(int from_id) {
		this.from_id = from_id;
	}

	public int getTo_id() {
		return to_id;
	}

	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
}
