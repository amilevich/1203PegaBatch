package com.revature.model;

public class ZipCodes {
	private int zipCode;
	private String city;
	private String State;
	public ZipCodes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ZipCodes(int zipCode, String city, String state) {
		super();
		this.zipCode = zipCode;
		this.city = city;
		State = state;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	@Override
	public String toString() {
		return "ZipCodes [zipCode=" + zipCode + ", city=" + city + ", State=" + State + "]";
	}
	

}
