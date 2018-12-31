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

	public Event(int event_id, LocalDate start_date, Timestamp start_time, String grade_received, String passing_grade,
			String description, String type_name, double coverage, Address location, String format_name,
			String default_passing_grade) {
		super();
		this.event_id = event_id;
		this.start_date = start_date;
		this.start_time = start_time;
		this.grade_received = grade_received;
		this.passing_grade = passing_grade;
		this.description = description;
		this.type_name = type_name;
		this.coverage = coverage;
		this.location = location;
		this.format_name = format_name;
		this.default_passing_grade = default_passing_grade;
	}

	public Event(LocalDate start_date, Timestamp start_time, String passing_grade, String description, String type_name,
			Address location, String format_name) {
		super();
		this.start_date = start_date;
		this.start_time = start_time;
		this.passing_grade = passing_grade;
		this.description = description;
		this.type_name = type_name;
		this.location = location;
		this.format_name = format_name;
	}

	@Override
	public String toString() {
		return "Event [event_id=" + event_id + ", start_date=" + start_date + ", start_time=" + start_time
				+ ", grade_received=" + grade_received + ", passing_grade=" + passing_grade + ", description="
				+ description + ", type_name=" + type_name + ", coverage=" + coverage + ", location=" + location
				+ ", format_name=" + format_name + ", default_passing_grade=" + default_passing_grade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((default_passing_grade == null) ? 0 : default_passing_grade.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + event_id;
		result = prime * result + ((format_name == null) ? 0 : format_name.hashCode());
		result = prime * result + ((grade_received == null) ? 0 : grade_received.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((passing_grade == null) ? 0 : passing_grade.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
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
		Event other = (Event) obj;
		if (Double.doubleToLongBits(coverage) != Double.doubleToLongBits(other.coverage))
			return false;
		if (default_passing_grade == null) {
			if (other.default_passing_grade != null)
				return false;
		} else if (!default_passing_grade.equals(other.default_passing_grade))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (event_id != other.event_id)
			return false;
		if (format_name == null) {
			if (other.format_name != null)
				return false;
		} else if (!format_name.equals(other.format_name))
			return false;
		if (grade_received == null) {
			if (other.grade_received != null)
				return false;
		} else if (!grade_received.equals(other.grade_received))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (passing_grade == null) {
			if (other.passing_grade != null)
				return false;
		} else if (!passing_grade.equals(other.passing_grade))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (type_name == null) {
			if (other.type_name != null)
				return false;
		} else if (!type_name.equals(other.type_name))
			return false;
		return true;
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

}
