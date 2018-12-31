package com.revature.trms.models;

public class GradingFormat {

	private String format_name;
	private String default_passing_grade;
	
	public GradingFormat() {
		super();
		
	}
	public GradingFormat(String format_name, String default_passing_grade) {
		super();
		this.format_name = format_name;
		this.default_passing_grade = default_passing_grade;
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
		return "GradingFormat [format_name=" + format_name + ", default_passing_grade=" + default_passing_grade + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((default_passing_grade == null) ? 0 : default_passing_grade.hashCode());
		result = prime * result + ((format_name == null) ? 0 : format_name.hashCode());
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
		GradingFormat other = (GradingFormat) obj;
		if (default_passing_grade == null) {
			if (other.default_passing_grade != null)
				return false;
		} else if (!default_passing_grade.equals(other.default_passing_grade))
			return false;
		if (format_name == null) {
			if (other.format_name != null)
				return false;
		} else if (!format_name.equals(other.format_name))
			return false;
		return true;
	}
	
}
