package com.revature.trms.models;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Event {

	private int event_id;
	private LocalDate start_date;
	private Timestamp start_time;
	private String grade_received;
	private String passing_grade;
	private String description;
	private double cost;

	// Event type information
	private String type_name;
	private double coverage;

	// Event location information(Address)
	private Address location;

	// Grading Format Information
	private String format_name;
	private String default_passing_grade;

	public Event() {
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public String getGrade_received() {
		return grade_received;
	}

	public void setGrade_received(String grade_received) {
		this.grade_received = grade_received;
	}

	public String getPassing_grade() {
		return passing_grade;
	}

	public void setPassing_grade(String passing_grade) {
		this.passing_grade = passing_grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getFormat_name() {
		return format_name;
	}

	public void setFormat_name(String format_name) {
		this.format_name = format_name;
	}

	public String getDefault_passing_grade() {
		return default_passing_grade;
	}

	public void setDefault_passing_grade(String default_passing_grade) {
		this.default_passing_grade = default_passing_grade;
	}

	@Override
	public String toString() {
		return "Event [event_id=" + event_id + ", start_date=" + start_date + ", start_time=" + start_time
				+ ", grade_received=" + grade_received + ", passing_grade=" + passing_grade + ", description="
				+ description + ", cost=" + cost + ", type_name=" + type_name + ", coverage=" + coverage + ", location="
				+ location + ", format_name=" + format_name + ", default_passing_grade=" + default_passing_grade + "]";
	}

	public Event(int event_id, LocalDate start_date, Timestamp start_time, String grade_received, String passing_grade,
			String description, double cost, String type_name, double coverage, Address location, String format_name,
			String default_passing_grade) {
		super();
		this.event_id = event_id;
		this.start_date = start_date;
		this.start_time = start_time;
		this.grade_received = grade_received;
		this.passing_grade = passing_grade;
		this.description = description;
		this.cost = cost;
		this.type_name = type_name;
		this.coverage = coverage;
		this.location = location;
		this.format_name = format_name;
		this.default_passing_grade = default_passing_grade;
	}
	
	
	
}
