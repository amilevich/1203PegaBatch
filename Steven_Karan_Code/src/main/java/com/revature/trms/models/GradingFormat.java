package com.revature.trms.models;

public class GradingFormat {
	private String format_name;
	private String default_passing_grade;
	
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
		return "GradingFormat [format_name=" + format_name + ", default_passing_grade=" + default_passing_grade + "]";
	}
	public GradingFormat(String format_name, String default_passing_grade) {
		super();
		this.format_name = format_name;
		this.default_passing_grade = default_passing_grade;
	}
	public GradingFormat() {
		super();
	}
	
}
