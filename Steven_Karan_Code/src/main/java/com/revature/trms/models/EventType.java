package com.revature.trms.models;

public class EventType {
	private String type_name;
	private double coverage;
	
	
	public EventType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EventType(String type_name, double coverage) {
		super();
		this.type_name = type_name;
		this.coverage = coverage;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public double getCoverage() {
		return coverage;
	}
	public void setCoverage(double coverage) {
		this.coverage = coverage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type_name == null) ? 0 : type_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventType other = (EventType) obj;
		if (Double.doubleToLongBits(coverage) != Double.doubleToLongBits(other.coverage))
			return false;
		if (type_name == null) {
			if (other.type_name != null)
				return false;
		} else if (!type_name.equals(other.type_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EventType [type_name=" + type_name + ", coverage=" + coverage + "]";
	}

}
