package com.revature.trms.models;

public class EventStats {
	String eventType;
	int count;
	double totalSpent;
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}
	@Override
	public String toString() {
		return "EventStats [eventType=" + eventType + ", count=" + count + ", totalSpent=" + totalSpent + "]";
	}
	public EventStats(String eventType, int count, double totalSpent) {
		super();
		this.eventType = eventType;
		this.count = count;
		this.totalSpent = totalSpent;
	}
	public EventStats() {
		super();
	}
}
