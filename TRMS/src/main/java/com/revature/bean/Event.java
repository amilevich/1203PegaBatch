package com.revature.bean;

public class Event {
	
	int eventId;
	float converage;
	String evenType;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int eventId, float converage, String evenType) {
		super();
		this.eventId = eventId;
		this.converage = converage;
		this.evenType = evenType;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public float getConverage() {
		return converage;
	}

	public void setConverage(float converage) {
		this.converage = converage;
	}

	public String getEvenType() {
		return evenType;
	}

	public void setEvenType(String evenType) {
		this.evenType = evenType;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", converage=" + converage + ", evenType=" + evenType + "]";
	}
}
